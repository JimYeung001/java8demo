package com.jim.lambda;

import java.time.LocalDate;

public class Person {
	
	public enum Sex{
		MALE, FEMALE
	}
	
	
	private String name;
	private LocalDate birthday;
	private Sex gender;
	private String email;
	private int age;
	
	public Person() {
		
	}
	
	public Person(String name, LocalDate birthday, Sex gender, String email, int age) {
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.email = email;
		this.age = age;
	}
	
	
	public void printPerson() {
		System.out.println("name:" + getName() +
				" Birthday: " + getBirthday() +
				" Gender: " + getGender() +
				" Email: " + getEmail() +
				" Age: " + getAge());
	}
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the birthday
	 */
	public LocalDate getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the gender
	 */
	public Sex getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Sex gender) {
		this.gender = gender;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
	

}
