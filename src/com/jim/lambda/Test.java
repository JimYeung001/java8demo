package com.jim.lambda;

public class Test {
	static class InstanceOuter {
		public InstanceOuter(int xx) {
			x = xx;
		}

		private int x;

		class InstanceInner {
			public void printSomething() {
				System.out.println("The value of x in my outer is " + x);
			}
		}
	}
	
	public static class StaticOuter {

		private static int x = 24;

		static class StaticInner {
			public void printSomething() {
				System.out.println("The value of x in my outer is " + x);
			}
		}

	}

	public static class InnerClassExamples {
		public static void main(String[] args) {
			InstanceOuter io =  new Test.InstanceOuter(12);

			// Is this a compile error?
			InstanceOuter.InstanceInner ii = io.new InstanceInner();

			// What does this print?
			ii.printSomething(); // prints 12

			// What about this?
			StaticOuter.StaticInner si = new StaticOuter.StaticInner();
			si.printSomething(); // prints 24
		}
	}
}
