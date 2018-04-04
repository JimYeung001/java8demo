package com.jim.lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.jim.lambda.Person.Sex;

public class RosterTest {
	
	//Approach 1: create a methods that search for persons that match one characteristic
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for(Person p: roster) {
			if(p.getAge() >=  age) {
				p.printPerson();
			}
		}
	}
	
	//Approach 2: create more generalized Search methods
	public static void printPersonsWithinAgeRange(List<Person> roster, int low, int high) {
		for(Person p: roster) {
			if(low <= p.getAge() && p.getAge() <= high) {
				p.printPerson();
			}
		}
	}
	
	//Approach 3: Specify search criteria code in a local class
	//Approach 4: Specify search criteria code in an anonymous class
	//Approach 5: Specify search criteria code with a Lambda Expression
	public static void printPersons(List<Person> roster, CheckPerson tester) {
		for(Person p: roster) {
			if(tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	
	//Approach 6: Use standard Functional interfaces with Lambda expressions
	public static void printPersonsWithPredicate(List<Person> roster,  Predicate<Person> tester) {
		for(Person p: roster) {
			if(tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	//Approach 7: Use lambda throughout your application
	public static void processPersons(List<Person> roster,
			Predicate<Person> tester, Consumer<Person> block) {
		for(Person p: roster) {
			if(tester.test(p)) {
				block.accept(p);
			}
		}
	}
	
	//Approach 7: Second example
	public static void processPersonWithFunction(List<Person> roster, 
			Predicate<Person> tester, 
			Function<Person, String> mapper,
			Consumer<String> block) {
		for(Person p: roster) {
			if(tester.test(p)) {
				String data = mapper.apply(p);
				block.accept(data);
			}
		}
	}
	
	//Approach 8: Use generics more Extensively
	public static <X, Y> void processElements(
			Iterable<X> source,
			Predicate<X> tester,
			Function<X, Y> mapper,
			Consumer<Y> block) {
		for(X p : source) {
			if(tester.test(p)) {
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}
	
	
	
	
	
	
	

	public static void main(String[] args) {

		List<Person> roster = PersonResource.createPeople();
		
		//Approach 1
		System.out.println("People older than 20");
		printPersonsOlderThan(roster, 20);
		
		//Approach 2:
		System.out.println("\nPeople between the aga of 14 and 30: ");
		printPersonsWithinAgeRange(roster, 14, 30);
		
		class CheckPersonEligibleForSelectiveService implements CheckPerson {
			@Override
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.FEMALE &&
						p.getAge() >= 18 &&
						p.getAge() <= 25;
			}
		}
		
		//Approach 3:
		System.out.println("\nPeople who are eligible for selective service: ");
		printPersons(roster, new CheckPersonEligibleForSelectiveService());
		
		
		//Approach 4:
		System.out.println("\nPeople who are eligible for special service + anonymous class");
		printPersons(roster, new CheckPerson() {
			@Override
			public boolean test(Person p) {
				return p.getGender() == Sex.FEMALE &&
						p.getAge() >=18 &&
						p.getAge() <=25;
			}
		});
		
		
		//Approach 5:
		System.out.println("\nPeople who are eligible for special service + (Lambda express)");
		printPersons(roster, 
				(Person p) -> p.getGender() == Sex.FEMALE &&
				p.getAge() >=18 && p.getAge() <=25);
		
		//Approach 6:
		System.out.println("\nPeople who are eligible selective service with Predicate Parameter: ");
		printPersonsWithPredicate(roster, p -> p.getGender() == Sex.FEMALE &&
				p.getAge() >=18 && p.getAge() <= 25);
		
		
		//Approach 7: 
		System.out.println("\nPeople who are eligible selective service with predicate and customer parameters");
		processPersons(roster, p -> p.getGender() == Sex.FEMALE && 
				p.getAge() >=18 && p.getAge() <= 25, p -> p.printPerson());
		
		//Approach 8:
		System.out.println("\nPeople who are eligible selective service + Generic version");
		processElements(roster, 
				p -> p.getGender() == Sex.FEMALE && p.getAge() >=18 && p.getAge() <=25, 
				p -> p.getEmail(), 
				email -> System.out.println(email)
		);
		
		//Approach 9:
		System.out.println("\nPeople who are eligible for selective service  with bulk data operations: ");
		roster.stream()
			.filter(p -> p.getGender() == Sex.FEMALE && p.getAge() >=18 && p.getAge() <=25)
			.map(p -> p.getEmail())
			.forEach(email -> System.out.println(email));
		
		
	}

}
