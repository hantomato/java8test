package java8.supplier;

import java.util.function.Supplier;

/**
 * The following code shows how to use Constructor as method reference for Supplier.
 * 
 * @author nmj
 *
 */
public class SupplierTest3 {

	public static void main(String...strings) {
		Employee employee = maker(Employee::new); 
		System.out.println(employee);
	}
	
	private static Employee maker(Supplier<Employee> fx) {
		return fx.get();
	}
	
	
}

class Employee {
	@Override
	public String toString() {
		return "A EMPLOYEE";
	}
}
