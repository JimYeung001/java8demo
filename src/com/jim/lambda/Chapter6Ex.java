package com.jim.lambda;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.jim.lambda.Dish.CaloricLevel;
import com.jim.lambda.Dish.Type;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Chapter6Ex {

	public static void main(String[] args) {

		System.out.print("Max calories dish: ");
		Dish.menu.stream()
//			.max(Comparator.comparing(Dish::getCalories))
			.collect(maxBy(Comparator.comparingInt(Dish::getCalories)))
			.ifPresent(System.out::println);
		
		System.out.print("Total Calories is : ");
		Integer total = Dish.menu.stream()
			.collect(summingInt(Dish::getCalories));
		System.out.println(total);
			
		System.out.print("Average Calories is : ");
		double average = Dish.menu.stream()
			.collect(averagingInt(Dish::getCalories));
		System.out.printf("%.2f",average);
		
		System.out.print("\nSummarizing int : ");
		IntSummaryStatistics collect = Dish.menu.stream()
			.collect(summarizingInt(Dish::getCalories));
		System.out.println(collect);
		
		System.out.print("\nJoining strings : ");
		String allDishes = Dish.menu.stream()
			.map(Dish::getName)
			.collect(joining(","));
		System.out.println(allDishes);
		
		System.out.print("reducing sum: ");
		Dish.menu.stream()
			.map(Dish::getCalories)
			.collect(reducing((i, j) -> i + j))
			.ifPresent(System.out::println);
		
		System.out.print("The most calories dish: ");
		Dish.menu.stream()
			.collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
			.ifPresent(System.out::println);
		
		List<Integer> reduce = Arrays.asList(1,2,3,4,5,6).stream()
			.reduce(new ArrayList<Integer>(), (List<Integer> l, Integer e) -> {
				l.add(e);
				return l;
			}, (List<Integer> l1, List<Integer> l2) -> {
				l1.addAll(l2);
				return l1;
			});
		
		System.out.println(reduce);
		
		String collect2 = Dish.menu.stream()
			.collect(reducing("", Dish::getName, (d1, d2)->d1 + d2));
		
		Map<Type, List<Dish>> collect3 = Dish.menu.stream()
			.collect(groupingBy(Dish::getType));
		System.out.println(collect3);
		
		Map<Integer, List<Dish>> collect4 = Dish.menu.stream()
			.collect(groupingBy(Dish::getCalories));
		System.out.println(collect4);
		
		//Group by
		Map<CaloricLevel, List<Dish>> collect5 = Dish.menu.stream().collect(
			groupingBy(dish ->{
				if(dish.getCalories() <=400) return CaloricLevel.DIET;
				else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
				else return CaloricLevel.FAT;
			})
		);
		System.out.println(collect5);
		
		// Two level grouping
		Map<Type, Map<CaloricLevel, List<Dish>>> collect6 = Dish.menu.stream().collect(
			groupingBy(Dish::getType, 
					groupingBy(dish -> {
						if(dish.getCalories() <=400) return CaloricLevel.DIET;
						else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
						else return CaloricLevel.FAT;
					}))
				);
		System.out.println(collect6);
		
		//Adapting the collector to a different type
		Map<Type, Dish> collect7 = Dish.menu.stream().collect(
			groupingBy(Dish::getType,
					collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
					Optional::get)));
		System.out.println(collect7);
		
	}

}
