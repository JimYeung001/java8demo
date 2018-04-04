package com.jim.lambda;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jim.lambda.Person.Sex;

public class PersonResource {

	private static final List<Person> people = new ArrayList<>(
			Arrays.asList(
					new Person("Jim", LocalDate.of(2014, Month.JANUARY, 1), Sex.MALE, "jim.yang@gmail.com", 10),
					new Person("Emmy", LocalDate.of(2018, Month.FEBRUARY, 12), Sex.FEMALE, "jim.yang@gmail.com", 50),
					new Person("Joy", LocalDate.of(2015, Month.APRIL, 9), Sex.FEMALE, "jim.yang@gmail.com", 11),
					new Person("JoyYY", LocalDate.of(2015, Month.APRIL, 9), Sex.FEMALE, "jim.yang@gmail.com", 20)));
	
	
	public static List<Person> createPeople(){
		return people;
	}
}
 