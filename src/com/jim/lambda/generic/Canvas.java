package com.jim.lambda.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Canvas  implements DoIt{

	public void draw(Shape s) {
		s.draw(this);
	}

	public void drawAll(List<? extends Shape> shapes) {
		for (Shape s : shapes) {
			s.draw(this);
		}
	}

	public static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
    	for(T o : a) {
    		c.add(o);
    	}
    }

	public static void addRectangle(List<? extends Shape> shapes) {

	}

	public Canvas() {
		List<? extends Shape> shapes = new ArrayList<>();
		Canvas.addRectangle(shapes);
	}

}