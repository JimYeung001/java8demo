package com.jim.lambda.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class DeadLokck implements Executor {
	
	static class Friend{
		private final String name;
		
		public Friend(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public synchronized void bow(Friend bower) {
			System.out.format("%s: %s has bowed to me! %n", this.name, bower.getName());
			bower.bowBack(this);
		}
		
		public synchronized void bowBack(Friend bower) {
			System.out.format("%s: %s has bowed to me! %n", this.name, bower.getName());
		}
		
	}
	
	public static void main(String[] args) {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		
		int id= ThreadLocalRandom.current().nextInt(1, 10);
		System.out.println("in random: " + id);
		
		DeadLokck lock = new DeadLokck();
		lock.execute(() -> {alphonse.bow(gaston); }); 
		lock.execute(() -> {gaston.bow(alphonse); });
		
//		lock.execute(new Runnable() {
//			public void run() { gaston.bow(alphonse);}
//		});
	}

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();		
	}

}
