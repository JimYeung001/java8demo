package com.jim.lambda.concurrent;

public class HelloRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello from thread!");
	}

	public static void main(String[] args) {
		new Thread(new HelloRunnable()).start();

	}

}
