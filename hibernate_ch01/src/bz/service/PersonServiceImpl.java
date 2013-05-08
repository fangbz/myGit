package bz.service;

import java.util.List;

import bz.dao.PersonDao;
import bz.dao.PersonDaoImpl;
import bz.hibernate.bean.Person;

public class PersonServiceImpl implements PersonService {

	@Override
	public void savePerson(Person person) {
		
		PersonDao personDao = new PersonDaoImpl();
		personDao.savePerson(person);
		
	}

	@Override
	public List<Person> listAllPersons() {
		PersonDao personDao = new PersonDaoImpl();
		return personDao.listAllPersons();
	}

	@Override
	public void deletePersonById(int id) {
		PersonDao personDao = new PersonDaoImpl();
		personDao.deletePersonById(id);
	}

	@Override
	public Person getPersonById(int id) {
		PersonDao personDao = new PersonDaoImpl();
		return personDao.getPersonById(id);
	}

	@Override
	public void updatePerson(Person person) {
		PersonDao personDao = new PersonDaoImpl();
		personDao.updatePerson(person);
	}

}
