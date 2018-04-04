package com.jim.lambda.concurrent;

public class HelloThread extends Thread {
	
	public void run() {
		System.out.println("Hello from thread run");
	}

	
	public static void main(String[] args) {
		(new HelloThread()).start();

	}

}
