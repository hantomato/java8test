package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  
 * @author nmj
 *
 * infinite stream
 * 
 */
public class StreamExample2 {
	
	static void testInfiniteStream() {
		Stream<Integer> numbers = Stream.iterate(60, n -> n + 10);
		numbers.forEach(x -> {
			System.out.println(x); 
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		);		
	}
	
	static void testEtc() {
		// 5 부터 10까지의 합
		int sum = IntStream.range(5, 10)
			.sum();
			
		System.out.println("5 ~ 10 합은 " + sum);
		
	 
		// IntStream java.util.stream.IntStream.range(int startInclusive, int endExclusive)
		IntStream.range(5, 10).forEach(System.out::println);
		
		System.out.println("");
		// IntStream java.util.stream.IntStream.rangeClosed(int startInclusive, int endInclusive)
		IntStream.rangeClosed(5, 10).forEach(System.out::println);		
	}
	
	static void testFlatMap() {
		List<String> phrases = Arrays.asList(
		        "sporadic perjury",
		        "confounded skimming",
		        "incumbent jailer",
		        "confounded jailer");

		System.out.println("\nFlatMap 하기전..");
		phrases.stream()
			.forEach(System.out::println);
		
		System.out.println("\nFlatMap 후..");
		phrases.stream()
			.flatMap(phrase -> Stream.of(phrase.split(" +")))	// Stream.of(phrase.split(" +")) => Stream<String>
			.forEach(System.out::println);
		
	}
	
	static void testFlatMap2() {
		Stream<Integer> st1 = Arrays.asList(1,2,3).stream();
		Stream<Integer> st2 = Arrays.asList(4,5,6).stream();
		Stream<Integer> st3 = Arrays.asList(7,8,9).stream();
		
		Arrays.asList(st1, st2, st3).stream()
			.flatMap(new Function<Stream<Integer>, Stream<Integer>>() {
			@Override
			public Stream<Integer> apply(Stream<Integer> t) {
				System.out.println("...");
				return t;
			}
		}).forEach(x -> System.out.println(">> " + x));
		
//		Arrays.asList(st1, st2, st3).stream()
//			.flatMap(x -> x)		// 여기서 첫번째 x 는 Stream of 1,2,3
//			.forEach(x -> System.out.println(">> " + x));	// 이거 주석 풀면 에러 발생.. stream has already been operated upon or closed 
		
		Arrays.asList(st1, st2, st3).stream()
			.flatMap(x -> Arrays.asList(100, 200, 300).stream())
			.forEach(x -> System.out.println(">> " + x));
	
		System.out.println("-----");
		Arrays.asList(1,2,3,4,5).stream()
			.flatMap(x -> {
				return Arrays.asList(x*x).stream();
			})
			.forEach(x -> System.out.println(">> " + x));
	
	}

	public static void main(String... argc) {
		
//		testInfiniteStream();
//		testEtc();
//		testFlatMap();
		testFlatMap2();

	}
}
