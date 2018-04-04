package com.jim.lambda.concurrent;

public class SimpleThreads {
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}
	
	private static class MessageLoop implements Runnable{

		@Override
		public void run() {
			String importantInfo[] = {
					"Meras eats oats",
					"Does eat oats",
					"Little lamb eat ivy",
					"A kid will eat ivy too"
			};

			for(int i=0 ; i < importantInfo.length; i++) {
				try {
					Thread.sleep(4000);
					threadMessage(importantInfo[i]);
				} catch (InterruptedException e) {
					threadMessage("I am not done yet!");
				}
			}
	
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		//Delay , in milliseconds before we interrupt messageLop
		//Thread (Default one hour)
		long patience = 1000 * 60 * 60;
		
		//If command line argument present, given patient
		if(args.length > 0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			}catch(NumberFormatException e) {
				System.out.println("Argument must be an Integer");
				System.exit(1);
			}
		}
		
		threadMessage("Starting messageLoop thread");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();
		threadMessage("Waiting  for MessageLoop thread to finish");
		
		//loop until MessageLoop thread exits
		while(t.isAlive()) {
			threadMessage("Still waiting ... ");
			//wait maximum 1 second for MessageLoop to finsh
			t.join(1000);
			if((System.currentTimeMillis() - startTime) > patience && t.isAlive()) {
				threadMessage("Tried of waiting!");
				t.interrupt();
				//Shouldn't be long now 
				//Wait indefinitely
				t.join();
			}
		}
		threadMessage("Finally!");
	}

}
