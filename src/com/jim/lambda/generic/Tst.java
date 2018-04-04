package com.jim.lambda.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tst {

	
	public static void main(String[] args) {
		List<Circle> circles = new ArrayList<>();
		circles.add(new Circle());
		Canvas canvas = new Canvas();
		DoIt doIt = new Canvas();
		
		List<Rectangle> rects = new ArrayList<>();
		rects.add(new Rectangle());
		canvas.drawAll(circles);
		canvas.drawAll(rects);
		
		
		Integer[] ia = new Integer[1];
		ia[0]= new Integer(20);
		Float[] fa = new Float[1];
		fa[0] = new Float(12.333);
		Number[] na = new Number[1];
		na[0] = new Float(12.993993933);
		Collection<Number> cn = new ArrayList<>();
		Canvas.fromArrayToCollection(ia, cn);
		Canvas.fromArrayToCollection(fa, cn);
		Canvas.fromArrayToCollection(na, cn);
		
		doIt.drawShap2();
		DoIt.drawStaticShape();
		
		
	}
}
