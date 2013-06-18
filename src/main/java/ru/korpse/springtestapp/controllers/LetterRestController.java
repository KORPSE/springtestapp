package ru.korpse.springtestapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.korpse.springtestapp.core.services.LetterService;
import ru.korpse.springtestapp.entities.Letter;

@Controller

@RequestMapping("/data/letters/")
public class LetterRestController {

	@Autowired
	private LetterService service;
	
	@RequestMapping(value = "/{id}/", method = RequestMethod.GET)
	@ResponseBody
	public Letter get(@PathVariable("id") long id) {
		return service.get(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(@RequestBody Letter item) {
		service.save(item);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", item.getId());
		return result;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Letter> list() {
		return service.list();
	}
	
	@RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(long id) {
		service.delete(id);
	}
}
