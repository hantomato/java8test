package java8.stream;

import java.util.Arrays;
import java.util.List;

public class test2 {

	public static void main(String... args) {
		
		List<String> myList = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c4", "c2");
		
		myList.stream()
			.filter(s -> {
				return s.startsWith("c");
				})
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);
	}
}
