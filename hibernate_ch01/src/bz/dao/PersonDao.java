package bz.dao;

import java.util.List;

import bz.hibernate.bean.Person;

public interface PersonDao {

	public void savePerson(Person person);
	
	public List<Person> listAllPersons();
	
	public void deletePersonById(int id);
	
	public Person getPersonById(int id);
	
	public void updatePerson(Person person);
}
