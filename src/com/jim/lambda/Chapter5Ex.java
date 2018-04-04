package com.jim.lambda;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.joining;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Chapter5Ex {

	public static void main(String[] args) throws Exception {
		Integer[] numbs = { 1, 2, 3, 4, 5 };
		Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300), 
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),	
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );	

		Stream<Integer> stream = Arrays.stream(numbs);
		List<Integer> collect = stream.map(i -> i * i).collect(toList());

		// collect.stream().forEach(i -> System.out.print(i + ","));

		List<Integer> num1 = Arrays.asList(1, 2, 3,4);
		List<Integer> num2 = Arrays.asList(3, 4);
		List<int[]> collect2 = num1.stream().flatMap(i -> num2.stream().map(j -> new int[] { i, j })).collect(toList());
		collect2.stream().forEach(i -> {
			System.out.print("[");
			Arrays.stream(i).forEach(j -> {
				System.out.print(j + " ");
			});
			System.out.print("],");
		});
		
		System.out.println(" \n");
		Stream<int[]> collect3 = num1.stream()
				.flatMap(i -> num2.stream().filter(j -> (j+i)%3==0)
				.map(j -> new int[] {i, j}));
		collect3.forEach(i ->Arrays.stream(i).forEach(n -> System.out.print(n + ",")));
		
		System.out.println(" \n");
		
		System.out.println(num1.stream().anyMatch(i -> i%2==0));
		System.out.println(num1.stream().allMatch(i -> i < 10));
		System.out.println(num1.stream().noneMatch(i -> i > 10));
		
		System.out.print("Check numer great than 2 is ");
		Integer orElse = num1.stream().filter(i -> i > 20)
			.findAny()
			.orElse(-1);
//			.ifPresent(System.out::println);
		System.out.println(orElse);
		
		System.out.println(" \n");
		num1.stream().map(x -> x * x)
					.filter(x -> x %3 == 0)
					.findAny()
					.ifPresent(System.out::println);
		
		Integer reduce = num1.stream().reduce(0, Integer::sum);
		System.out.println("sum: " + reduce);
		Integer reduce2 = num1.stream().reduce(1, (a, b) -> a * b);
		System.out.println("muliply: " + reduce2);
		num1.stream().reduce(Integer::sum).ifPresent(System.out::println);
		num1.stream().reduce((a,b)-> a*b).ifPresent(System.out::println);
		num1.stream().reduce(Integer::max).ifPresent(System.out::println);
		num1.stream().reduce(Integer::min).ifPresent(System.out::println);


		System.out.println("size: "  + num1.stream().reduce(0, (a, b) -> a + 1));
		System.out.println("size: "  + num1.stream().map(i -> 1).reduce(0, (a, b) -> a + b));
		System.out.println("size: " + num1.stream().count());
		System.out.println("sum: " + num1.parallelStream().reduce(Integer::sum).get());
		
		List<Transaction> collect4 = transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		collect4.stream().forEach(System.out::println);
		
		Set<String> collect5 = transactions.stream()
				.map(t -> t.getTrader().getCity())
//				.distinct()
				.collect(toSet());
		collect5.stream().forEach(System.out::println);
		
		System.out.println(" \n");
		
		List<Trader> collect6 = transactions.stream()
				.map(t -> t.getTrader())
				.filter(d -> d.getCity().equals("Cambridge"))
				.sorted(comparing(Trader::getName).reversed())
				.distinct()
				.collect(toList());
		collect6.stream().forEach(System.out::println);
		
		transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Milan"))
				.findAny()
				.ifPresent(System.out::println);
		
		
		List<Integer> collect7 = transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.collect(toList());
		collect7.stream().forEach(System.out::println);
		
		
		transactions.stream()
				.max(comparing(Transaction::getValue))
				.ifPresent(System.out::println);
		transactions.stream()
				.map(Transaction::getValue)
				.reduce(Integer::min)
				.ifPresent(System.out::println);
		
		String reduce3 = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.sorted()
//				.reduce("", (n1, n2) -> n1 + " " + n2);
				.collect(joining(",")); //equivalent above
		System.out.println("trade names: " + reduce3);
		
		transactions.stream()
				.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2)
				.map(t -> t.getValue())
				.ifPresent(System.out::println);
		int sum = transactions.stream()
				.mapToInt(Transaction::getValue)
				.sum();
		System.out.println("Sum: " + sum);
		
		IntStream intStream = transactions.stream()
		.mapToInt(Transaction::getValue);
		Stream<Integer> boxed = intStream.boxed();
		boxed.reduce(Integer::sum)
			.ifPresent(System.out::println);
		
		OptionalInt max = transactions.stream()
				.mapToInt(Transaction::getValue)
				.max();
		int intElse = max.orElse(1);
		System.out.println(intElse);
		
		IntStream intStream2 = IntStream.range(1, 100)
				.filter(i -> i%2 == 0);
		intStream2.forEach(n -> System.out.print(n + ","));
		
		System.out.println(" \n");
		//Pythagorean Triple
		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
				.boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
							.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
							.mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a*a + b*b)})
						);
		pythagoreanTriples.limit(5).forEach(t ->System.out.println(t[0] + "," + t[1] + "," + t[2]));
		
		Stream<int[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
							.mapToObj(b -> new double[] {a, b, Math.sqrt(a*a + b*b)})
							.filter(t -> t[2]%1 == 0))
				.map(f -> new int[] {(int)f[0],(int)f[1],(int)f[2]});
		System.out.println(" \n");
		pythagoreanTriples2.limit(5).forEach(t ->System.out.println(t[0] + "," + t[1] + "," + t[2]));
		
		//FIbonacci number
		System.out.println("Odd Number:");
		Stream.iterate(1, n -> n +2)
				.limit(10)
				.forEach(n -> System.out.print(n + ","));
		System.out.println("Fibonacci Number:");
		Stream.iterate(new long[] {0, 1}, n -> new long[] {n[1], (n[0] + n[1])})
				.limit(10)
				.map(t -> t[0])
//				.forEach(t -> System.out.print("(" + t[0] + ", " + t[1] +  ")" + " "));
				.forEach(t -> System.out.print(t + ", "));
		
		//Generate
		System.out.println("Generated Number:");
		List<Double> collect8 = Stream.generate(Math::random)
				.limit(5)
				.collect(toList());
//				.forEach(n -> System.out.print(n + ", "));
	}

}
