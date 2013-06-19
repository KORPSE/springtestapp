package ru.korpse.springtestapp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import ru.korpse.springtestapp.utils.exceptions.ResourceNotFoundException;
import ru.korpse.springtestapp.utils.exceptions.SaveFailException;

@Data
public class MyExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		if (ex instanceof ResourceNotFoundException) {
			response.setStatus(404);
		} else if (ex instanceof SaveFailException){
			response.setStatus(400);
		} else {
			response.setStatus(500);
		}
		
		ModelAndView result = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		result.setView(view);
		result.getModel().put("error", ex.getMessage());
		
		return result;
	}
}
