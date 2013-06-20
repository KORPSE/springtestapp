package ru.korpse.springtestapp.core.services;

import java.io.IOException;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ru.korpse.springtestapp.core.dao.FileDao;
import ru.korpse.springtestapp.entities.File;
import ru.korpse.springtestapp.utils.exceptions.SaveFailException;

@Service
public class FileService {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private FileDao dao;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public File get(long id) {
		return dao.get(id);
	}
	
	@Transactional
	public void save(File item) {
		dao.save(item);
	}
	
	@Transactional
	public void delete(long id) {
		dao.delete(id);
	}
	
	@Transactional
	public File upload(CommonsMultipartFile fileUpload) {
		if (!(fileUpload.getContentType().toLowerCase().equals("image/jpeg") ||
				fileUpload.getContentType().toLowerCase().equals("application/pdf"))) {
			throw new SaveFailException("Only JPEG and PDF type files allowed");
		}
		try {
			File file = new File();
            Blob blob = Hibernate.getLobCreator(getSession())
            		.createBlob(fileUpload.getInputStream(), fileUpload.getSize());
 
            file.setName(fileUpload.getOriginalFilename());
            file.setData(blob);
            file.setContentType(fileUpload.getContentType());
    		return file;
		} catch (IOException e) {
            throw new SaveFailException(String.format("File upload failed: %s", fileUpload.getName()));
        }
	}
}
