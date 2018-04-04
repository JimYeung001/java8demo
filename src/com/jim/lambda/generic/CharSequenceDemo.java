package com.jim.lambda.generic;

public class CharSequenceDemo implements CharSequence {

	private String s;

	public CharSequenceDemo(String s) {
		this.s = s;
	}

	private int fromEnd(int i) {
		return s.length() - 1 - i;
	}
	
	public int lengh() {
		return s.length();
	}
	

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char charAt(int index) {
		if(index < 0 || index >= this.s.length()) {
			throw new StringIndexOutOfBoundsException(index);
		}

		return s.charAt(fromEnd(index));
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		if(start < 0) {
			throw new StringIndexOutOfBoundsException(start);
		}
		
		if(end > s.length()) {
			throw new StringIndexOutOfBoundsException(end);
		}
		
		if(start > end) {
			throw new StringIndexOutOfBoundsException(start - end);
		}
		
		StringBuilder sub = new StringBuilder(s.subSequence(fromEnd(end), fromEnd(start)));
		return sub.reverse();
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder(this.s);
		return s.reverse().toString();
	}
	
	private static int random(int max) {
		return (int) Math.round(Math.random() * (max+1));
	}
	
	public static void main(String[] args) {
		CharSequenceDemo s =  new CharSequenceDemo("Write a class that implements CharSequence interface found in the java.lang package");
		
		for(int i=0 ; i < s.lengh() ; i++) {
			System.out.print(s.charAt(i));
		}
		
		System.out.println("");
		
		int start = random(s.lengh() -1);
		int end = random(s.lengh() -1 - start) + start;
		
		System.out.println(s.subSequence(start, end));
		
		System.out.println(s.toString());
	}

}
