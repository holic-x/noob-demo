package com.noob.web.model;
/** 
 * javaBean：Person领域对象      
 * id、username、password、sex、descr、address
 */
public class Person {
	private String id;
	private String username;
	private String password;
	private String sex;
	private String descr;
	private Address address;
	public Person() {
		super();
	}
	public Person(String id, String username, String password, String sex, String descr, Address address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.descr = descr;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", password=" + password + ", sex=" + sex + ", descr="
				+ descr + ", address=" + address + "]";
	}
}


