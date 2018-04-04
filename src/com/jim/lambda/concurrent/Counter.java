package com.jim.lambda.concurrent;

public class Counter {
	
	private int c= 0;
	
	public void increment() {
		c++;
	}
	
	public void decrement() {
		c--;
	}
	
	public int getValue() {
		return c;
	}

}
