package com.jim.lambda.concurrent;

public class SleepMessages {

	public static void main(String[] args){
		String importantInfo[] = {
				"Meras eats oats",
				"Does eat oats",
				"Little lamb eat ivy",
				"A kid will eat ivy too"
		};
		
		for(int i=0 ; i < importantInfo.length; i++) {
			try {
				Thread.sleep(4000);
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
			} catch (InterruptedException e) {
				return;
			}
			System.out.println(importantInfo[i]);
		}
	}
}
