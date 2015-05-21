package java8.supplier;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class SupplierTest4 {

	public static void main(String[] args) {
		Supplier<Student> studentGenerator = SupplierTest4::employeeMaker;

		for (int i = 0; i < 10; i++) {
			System.out.println("#" + i + ": " + studentGenerator.get());
		}
	}

	public static Student employeeMaker() {
		return new Student("A", 2);
	}
	Predicate<String> i  = (s)-> s.length() > 5;

}

class Student {
	public String name;
	public double gpa;

	Student(String name, double g) {
		this.name = name;
		this.gpa = g;
	}

	@Override
	public String toString() {
		return name + ": " + gpa;
	}
}