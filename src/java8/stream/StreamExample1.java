package java8.stream;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author tomatomb
 *
 * stream
 *	- http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 *
 * java8의 stream을 배워보자.
 * 중계 연산 api
 *  - map(Function<? super T, ? extends R>
 *  - filter(Predicate<? super T> predicate)
 *  - mapToInt, mapToLong, mapToDouble
 *  - flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
 *  - peek(Consumer<? super T> action)
 *  - skip
 *  - limit
 *	- sorted
 *  - distince : 중복값 제거
 *  
 * 종단 연산 api
 *	- foreach(Consumer<? super T> con)
 *	- long count()
 *  - Optional<T> max(Comparator<? super T> comparator)
 *  - Optional<T> min(Comparator<? super T> comparator)
 *  - boolean allMatch(Predicate<? super T> predicate)
 *  - boolean anyMatch(Predicate<? super T> predicate)
 *  - boolean noneMatch(Predicate<? super T> predicate)
 *  - sum(), min(), max(), average()
 *
 *	- reduce, collect
 *
 * java8 function interface
 *  - http://radar.oreilly.com/2014/08/java-8-functional-interfaces.html
 *  
 * 	- Predicate<T>			boolean test(T t);
 * 	- Consumer<T>			void accept(T t);
 * 	- Function<T, R>		R apply(T t);
 * 	- Supplier<T>			T get();
 *  - Comparator<T>			int compare(T o1, T o2);
 *  - ToLongFunction<T>		long applyAsLong(T value);
 * 
 *  아래껀 복잡하네.
 * 	- UnaryOperator<T> extends Function<T, T>			static <T> UnaryOperator<T> identity() {
 * 	- BinaryOperator<T> extends BiFunction<T,T,T>		public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
 * 
 *
 */

public class StreamExample1 {
	
	// 네이버 영화배우 랭킹 데이터 활용.
	static long staticManActorTotalAge = 0;

	static class Actor {
		public int ranking;
		public String name;
		public String sex;
		public int birth;
		public String nationality;
		
		public int getRanking() {
			return ranking;
		}
		public String getName() {
			return name;
		}
		public String getSex() {
			return sex;
		}
		public int getBirth() {
			return birth;
		}
		public String getNationality() {
			return nationality;
		}
		public int getAge() {
			return 2015 - getBirth();
		}

		public Actor(int ranking, String name, String sex, int birth, String nationality) {
			this.ranking = ranking;
			this.name = name;
			this.sex = sex;
			this.birth = birth;
			this.nationality = nationality;
		}
		
		public static int sortBirth(Actor a, Actor b) {
			if (a.birth < b.birth){
				return 1;
			} else if (a.birth == b.birth) {
				return 0;
			} else {
				return -1;
			}
		}
		
		public static void printThis(Actor a) {
			System.out.println("[" + a.ranking + "] " + a.name);
		}
	}

	// initialize data
	public static ArrayList<Actor> makeDummy() {
		ArrayList<Actor> actors = new ArrayList<>();
		actors.add(new Actor(1, "스칼렛 요한슨", "여", 1984, "미국"));
		actors.add(new Actor(2, "톰 하디", "남", 1977, "미국"));
		actors.add(new Actor(3, "조지 밀러", "남", 1945, "미국"));
		actors.add(new Actor(4, "샤를리즈 테론", "여", 1975, "미국"));
		actors.add(new Actor(5, "하주희", "여", 1982, "한국"));
		actors.add(new Actor(6, "니콜라스 홀트", "남", 1989, "미국"));
		actors.add(new Actor(7, "클로이 모레츠", "여", 1997, "미국"));
		actors.add(new Actor(8, "임지연", "여", 1990, "한국"));
		actors.add(new Actor(9, "엘리자베스 올슨", "여", 1989, "미국"));
		actors.add(new Actor(10, "이유영", "여", 1989, "한국"));
		
		actors.add(new Actor(11, "콜린 퍼스", "남", 1960, "미국"));
		actors.add(new Actor(12, "채민서", "여", 1981, "한국"));
		actors.add(new Actor(13, "박수진", "여", 1985, "한국"));
		actors.add(new Actor(14, "오세홍", "남", 1951, "한국"));
		actors.add(new Actor(15, "민규동", "남", 1970, "한국"));
		return actors;
	}
	
	
	public static void main(String... args) {
	
		
		// exercise
		// - 모든 배우를 랭킹순으로 출력하자.
		// - 랭킹 15위 내의 배우들중에 남자배우는 몇명인가?
		// - 모든 여자배우들의 이름을 출력하라.
		// - 남자배우 나이를 모두 합해보자.

//		 * 	- Predicate<T>			boolean test(T t);
//		 * 	- Consumer<T>			void accept(T t);
//		 * 	- Function<T, R>		R apply(T t);
//		 * 	- Supplier<T>			T get();
//		 *  - Comparator<T>			int compare(T o1, T o2);

		
		System.out.println("\n>> 모든 배우의 이름 출력");
		makeDummy().stream()
			.forEach(e -> System.out.println(e.name));
		
		System.out.println("\n>> 모든 배우를 랭킹순으로 랭킹과 이름 출력");
		makeDummy().stream()
			.sorted((x,y) -> { 
				if (x.ranking > y.ranking)
					return 1;
				else if (x.ranking == y.ranking)
					return 0;
				else
					return -1;
			})
			.forEach(e -> System.out.println(e.ranking + " - " + e.name));
		
		System.out.println("\n>> 남자 배우의 이름 출력");
		makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.forEach(e -> System.out.println(e.name));

		System.out.println("\n>> 남자 배우의 이름 출력 - map 사용1");
		makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.map(x -> x.name)
			.forEach(e -> System.out.println(e));

		System.out.println("\n>> 남자 배우의 이름 출력 - map 사용2");
		makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.map(x -> String.format("%s(age:%d)", x.name, (2015-x.birth)))
			.forEach(e -> System.out.println(e));
		
		System.out.println("\n>> 남자 배우는 총 몇명?");
		long cntManActor = makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.count();
		System.out.println("  남자 배우 숫자 : " + cntManActor);

		System.out.println("\n>> 남자 배우의 나이 출력");
		makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.forEach(e -> System.out.println(e.name + " : " + (2015 - e.getBirth())));
		
		System.out.println("\n>> 남자 배우의 나이 총합");
		
		staticManActorTotalAge = 0;
		makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.forEach(e -> {
				staticManActorTotalAge += (2015 - e.getBirth());
			});
		System.out.println("  총합1 : " + staticManActorTotalAge + " forEach 사용");
		
		long sum = makeDummy().stream()
			.filter(x -> x.sex.equals("남"))
			.mapToLong(x -> (2015 - x.getBirth()))
			.sum();
		
		System.out.println("\n>>제일 어린 배우");

		Optional<Actor> min = makeDummy().stream()
			.min(Actor::sortBirth);
			
		if (min.isPresent())
			System.out.println("제일 어린 배우는 : " + min.get().name + "(" + min.get().getAge() + ")");
		else
			System.out.println("제일 어린 배우 없음");

		System.out.println("\n>> 50살 이상의 남자 배우의 나이 총합");
		long sum2 = makeDummy().stream()
				.filter(x -> x.sex.equals("남"))
				.mapToLong(x -> (2015 - x.getBirth()))
				.filter(x -> x >= 50)
				.sum();
			
			System.out.println(sum2);
			}
	
	
	
	
}
