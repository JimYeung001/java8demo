package com.jim.lambda.generic;

public interface DoIt {
	
	void draw(Shape shape);
	
	default void drawShap() {
		System.out.println("Drawing default");
	}
	
	default void drawShap2() {
		drawStaticShape();
		System.out.println("Drawing default 2");
	}
	
	static void drawStaticShape() {		
		System.out.println("This is from static method");
	}

}
