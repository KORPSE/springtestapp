package ru.korpse.springtestapp.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.korpse.springtestapp.entities.File;
import ru.korpse.springtestapp.utils.exceptions.ResourceNotFoundException;

@Repository
public class FileDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public File get(long id) {
		File result = (File) getSession().get(File.class, id); 
		if (result == null) {
			throw new ResourceNotFoundException(String.format("File #%d not found in database", id));
		}
		return result;
	}
	
	@Transactional
	public void save(File item) {
		getSession().save(item);
	}
	
	@Transactional
	public void delete(long id) {
		getSession().delete(get(id));
	}

}
