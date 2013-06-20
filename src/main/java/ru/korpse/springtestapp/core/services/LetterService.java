package ru.korpse.springtestapp.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.korpse.springtestapp.core.dao.LetterDao;
import ru.korpse.springtestapp.entities.Letter;
import ru.korpse.springtestapp.utils.exceptions.SaveFailException;

@Service
public class LetterService {
	@Autowired
	private LetterDao dao;
	
	@Transactional(readOnly = true)	
	public Letter get(long id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)	
	public List<Letter> list() {
		return dao.list();
	}
	
	@Transactional
	public void save(Letter item) {
		dao.save(item);
	}
	
	@Transactional
	public void update(Letter item) {
		Letter existingItem = dao.get(item.getId());
		if (existingItem.isPublished() && !item.isPublished()) {
			throw new SaveFailException("Letter can't be unpublished");
		}
		dao.update(item);
	}
	
	@Transactional
	public void delete(long id) {
		dao.delete(id);
	}
	
}
