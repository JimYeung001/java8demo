package com.jim.lambda;

import java.util.stream.*;

public class ParalleStreams {

	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 0L; i < n; i++) {
			result += 1;
		}
		return result;
	}

	public static long sequentialSum(long n) {
		return Stream.iterate(0L, i -> i + 1).limit(n).reduce(Long::sum).get();
	}

	public static long parallelSum(long n) {
		return Stream.iterate(0L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
	}

	public static long rangedSum(long n) {
		return LongStream.rangeClosed(1L, n).reduce(Long::sum).getAsLong();
	}

	public static long parallelRangedSum(long n) {
		return LongStream.rangeClosed(1L, n).parallel().reduce(0L, (a, b) -> a + b);
	}

	public static long sideEffectSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1L, n).forEach(accumulator::add);
		return accumulator.total;
	}
	
	public static long sideEffectParallelSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1L, n).parallel().forEach(accumulator::add);
		return accumulator.total;
	}

	public static class Accumulator {
		private long total = 0;

		public void add(long value) {
			total += value;
		}
	}

}
