package top.lrshuai.es.entity;

import java.util.Date;


public class Person {
	private String id;
	private String name;
	private int age;
	private Date date;
	private String country;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Person( String name, int age,  Date date, String country) {
		super();
		this.name = name;
		this.age = age;
		this.date = date;
		this.country = country;
	}
	public Person(String id, String name, int age,Date date, String country) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", date=" + date
				+ ", country=" + country + "]";
	}
	
	
}
