package ru.korpse.springtestapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/")
public class FirstController {
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Hello(@ModelAttribute("today") String today, 
			   ModelMap model, HttpServletRequest request) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy MMMMMMM dd, hh:mm:ss, E", RequestContextUtils.getLocale(request));
		model.addAttribute("today", dt.format(new Date()));
		return "page";
	}

}
