package bz.hibernate.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class People {

	private Long id;
	
	private String name;
	
	private String password;
	
	private int telphone;
	
	private char gender;//  M  F
	
	private boolean graduation;// 是否毕业
	
	private Date birthday;
	
	private Timestamp marryTime; // 结婚时间
	
	private byte[] file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelphone() {
		return telphone;
	}

	public void setTelphone(int telphone) {
		this.telphone = telphone;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public boolean isGraduation() {
		return graduation;
	}

	public void setGraduation(boolean graduation) {
		this.graduation = graduation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Timestamp getMarryTime() {
		return marryTime;
	}

	public void setMarryTime(Timestamp marryTime) {
		this.marryTime = marryTime;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
	
}
