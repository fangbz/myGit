package bz.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import bz.ognl.Cat;
import bz.ognl.Person;

import com.opensymphony.xwork2.ActionSupport;

public class OgnlAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware {
	
	private Map<String, Object> requestMap;
	private Map<String, Object> sessiontMap; 
	private Map<String, Object> appliactionMap; 
	private List<Person> list = new ArrayList<Person>();

	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		
		// Thread.sleep(20000);
		
		sessiontMap.put("username", username);
		sessiontMap.put("password", password);
		
		appliactionMap.put("username", username);
		appliactionMap.put("password", password);
		
		Cat cat1 = new Cat("cat1",2,"black");
		Cat cat2 = new Cat("cat2",3,"write");
		
		String[] friends1=  {"f11","f12","f13"};
		String[] friends2=  {"f21","f22","f23"};
		
		Map<String,String> map1 = new HashMap<String,String>();
		Map<String,String> map2 = new HashMap<String,String>();
		map1.put("map1", "map1");
		map1.put("map2", "map2");
		map2.put("hello1", "hello1");
		map2.put("hello2", "hello2");
		
		Person p1 = new Person("zhangsan", 20, friends1, "beijing1", cat1, map1);
		Person p2 = new Person("lisi", 30, friends2, "beijing2", cat2, map2);
		list.add(p1);
		list.add(p2);
		
		return super.execute();
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		   this.requestMap = arg0;
		   System.out.println("set request");
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessiontMap = arg0;
		 System.out.println("set session");
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.appliactionMap = arg0;
		 System.out.println("set appliaction");
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
	public List<Person> getList() {
		return list;
	}
	public void setList(List<Person> list) {
		this.list = list;
	}
}
