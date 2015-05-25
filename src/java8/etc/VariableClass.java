package java8.etc;

/**
 * inner class example.
 * 
 * @author nmj
 *
 */
public class VariableClass {
	
	class MyInnerClass {
		@Override
		public String toString() {
			return "Class name is" + getClass().getName();
		}
	}
	
	static class MyStaticInnerClass {
		@Override
		public String toString() {
			return "Class name is" + getClass().getName();
		}
	}
	
	public static void main(String...strings) {
		
		class MyLocalInnerClass {						// local class.
			@Override
			public String toString() {
				return "Class name is" + getClass().getName();
			}
		}
		
		
//		MyInnerClass instanceClass = new MyInnerClass();			// 에러나네. 이건 instance 못만드나?
		MyStaticInnerClass staticClass = new MyStaticInnerClass();
		MyLocalInnerClass localClass = new MyLocalInnerClass();
		
//		System.out.println(instanceClass);
		System.out.println(staticClass);
		System.out.println(localClass);

	}

}
