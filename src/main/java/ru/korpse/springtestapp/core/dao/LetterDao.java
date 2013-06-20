package ru.korpse.springtestapp.core.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.korpse.springtestapp.entities.Letter;
import ru.korpse.springtestapp.utils.exceptions.ResourceNotFoundException;

@Repository
public class LetterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional(readOnly = true)
	public Letter get(long id) {
		Letter result = (Letter) getSession().get(Letter.class, id);
		if (result == null) {
			throw new ResourceNotFoundException(String.format("Letter #%d not found in database", id));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Letter> list() {
		return getSession().createQuery("from Letter").list();
	}

	@Transactional
	public void save(Letter item) {
		if (item == null) {
			return;
		}
		getSession().save(item);
	}
	
	@Transactional
	public void update(Letter item) {
		if (item == null) {
			return;
		}
		getSession().merge(item);
	}
	@Transactional
	public void delete(long id) {
		getSession().delete(get(id));
	}
}
