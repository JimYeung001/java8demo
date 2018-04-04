package com.jim.lambda;

import java.util.function.Function;

public class Chapter7Ex {

	private static final Long range = 10_000_000L;
	public static void main(String[] args) {
//		System.out.println("Sequential sum done in: " + 
//				measureSumPerf(ParalleStreams::sequentialSum, range) + " Seconds");
		System.out.println("Iterative sum done in: " + 
				measureSumPerf(ParalleStreams::iterativeSum, range) + " Seconds");
//		System.out.println("Parallel sum done in: " + 
//				measureSumPerf(ParalleStreams::parallelSum, range) + " Seconds");
		System.out.println("range close sum done in: " + 
				measureSumPerf(ParalleStreams::rangedSum, range) + " Seconds");
		System.out.println("Parallel range close sum done in: " + 
				measureSumPerf(ParalleStreams::parallelRangedSum, range) + " Seconds");
		System.out.println("side effect sum done in: " + 
				measureSumPerf(ParalleStreams::sideEffectSum, range) + " Seconds");
		System.out.println("side effect parallel sum done in: " + 
				measureSumPerf(ParalleStreams::sideEffectParallelSum, range) + " Seconds");

	}

	private static long measureSumPerf(Function<Long, Long>adder, long n) {
		long fastest = Long.MAX_VALUE;
		for(int i=0;i< 10;i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start)/ 1_000_000;
			System.out.println("Result " + sum);
			if(duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

}
