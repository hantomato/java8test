package java8.lambda;
/**
 * 
 * lambda example
 * 
 * @author tomatomb
 *
 */
public class Calculator {
	  

	interface IntegerMath {
		int operation(int a, int b);
	}
	
	public static int doingOperation(int a, int b, IntegerMath op) {
		return op.operation(a, b);
	}
		
	public static void main(String... args) {
		
		// 아래는 lambda 쓴거
		IntegerMath addition = (a, b) -> a + b;
		IntegerMath subtraction = (a, b) -> a - b;
		
		int ret1 = doingOperation(11, 22, addition);
		int ret2 = doingOperation(100, 10, subtraction);
		System.out.println("ret1 : " + ret1);
		System.out.println("ret2 : " + ret2);
		
		// lambda가 없다면 아래처럼 implements 를 하나 만들어야한다.
		IntegerMultiply multiply = new IntegerMultiply();
		int ret3 = doingOperation(3, 5, multiply);
		int ret4 = doingOperation(3, 11, new IntegerMultiply());
		System.out.println("ret3 : " + ret3);
		System.out.println("ret4 : " + ret4);
	}
	
	public static class IntegerMultiply implements IntegerMath {
		@Override
		public int operation(int a, int b) {
			return a * b;
		}
	}
}
