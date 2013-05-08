package bz.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bz.hibernate.bean.Person;
import bz.service.PersonService;
import bz.service.PersonServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport{


	private String username;
	private String password;
	private int age; 
    private int id; 
	
	
	
	
	
	public String savePerson(){
		
		PersonService personService = new PersonServiceImpl();
		
		java.sql.Date registerDate = new java.sql.Date(new java.util.Date().getTime());
		Person p = new Person();
		p.setUsername(username);
		p.setPassword(password);
		p.setAge(age);
		p.setRegisterdate(registerDate);
		personService.savePerson(p);
		
		List<Person> list = personService.listAllPersons();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return SUCCESS;
		
	}
	
	public String deletePerson(){
		PersonService personService = new PersonServiceImpl();
		
		personService.deletePersonById(id);
		
		List<Person> list = personService.listAllPersons();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return SUCCESS;
		
	}
	public String getPerson(){
		PersonService personService = new PersonServiceImpl();
		Person person = personService.getPersonById(id);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("person", person);
		return SUCCESS;
		
	}
	
	public String updatePerson(){
		PersonService personService = new PersonServiceImpl();
		Person person = personService.getPersonById(id);
		person.setAge(age);
		person.setUsername(username);
		person.setPassword(password);
		personService.updatePerson(person);


        List<Person> list = personService.listAllPersons();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", list);
		return SUCCESS;
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
