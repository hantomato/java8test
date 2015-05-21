package java8.supplier;

/**
 * java.util.function.Supplier 가 무엇인지 궁금해서 알아봤다.
 * 
 * lambda로 코드조각 하나 만들어두고, get()을 이용해서 여기저기서 사용하겠다는 것인가?
 * 
 * http://www.java2s.com/Tutorials/Java/java.util.function/Supplier/index.htm
 *
 * @author nmj
 *
 */
public class SupplierTest {

	public static void main(String...strings) {
		java.util.function.Supplier<String> m1 = () -> "hello world 1";		// 이게 Supplier 예제. get() 에서 수행할 코드를 lambda로 작성해둔것이네.		
		System.out.println(m1.get());
		
		MyCustomSupplier<String> m2 = () -> "hello world 2";				// Supplier 같은 클래스를 내가 만들어봄. 일단 차이가 없어보임.
		System.out.println(m2.get());
		
		MyCustomSupplier<Integer> m3 = () -> 77;							// 타입도 변경해봄.
		System.out.println(m3.get());

		testInterface m4 = () -> "hello world 3";							// 
		System.out.println(m4.get());
		
	}
	
	public interface MyCustomSupplier<T> {

	    T get();
	}
	
	public interface testInterface {
		String get();
	}
	
}
