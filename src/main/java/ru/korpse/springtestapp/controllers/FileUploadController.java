package ru.korpse.springtestapp.controllers;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ru.korpse.springtestapp.core.services.FileService;
import ru.korpse.springtestapp.entities.File;
import ru.korpse.springtestapp.utils.exceptions.SaveFailException;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	private FileService service;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public File handleFileUpload(HttpServletRequest request,
			@RequestParam CommonsMultipartFile fileUpload) throws Exception {

		File result = service.upload(fileUpload);
		service.save(result);

		return result;
	}
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String download(@PathVariable("id")
            long id, HttpServletResponse response) {
         
        File item = service.get(id);
        try {
			response.setHeader("Content-Disposition", "inline;filename=\"" + item.getName() + "\"");
            OutputStream out = response.getOutputStream();	
            response.setContentType(item.getContentType());
            IOUtils.copy(item.getData().getBinaryStream(), out);
            out.flush();
            out.close();
         
        } catch (Exception e) {
        	throw new SaveFailException(String.format("Fail during access uploaded file #%d", id));
        }
         
         
        return null;
    }
}
