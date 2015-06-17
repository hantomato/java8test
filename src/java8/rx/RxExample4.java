package java8.rx;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * concatWith
 * 
 * flatMap
 * public final <R> Observable<R> flatMap(Func1<? super T,? extends Observable<? extends R>> func)
 * 
 * concatMap
 * public final <R> Observable<R> concatMap(Func1<? super T,? extends Observable<? extends R>> func)
 * 
 * 
 * @author nmj
 *
 */
public class RxExample4 {
	
	public static void test1() {
		
		Observable<String> obs1 = Observable.just("test1");
		Observable<String> obs2 = Observable.just("test2", "test3");
		Observable<String> obs3 = Observable.just("test4");
		
		System.out.println("---");
		obs1.concatWith(obs2).subscribe(a -> System.out.println(a));

		System.out.println("---");
		obs2.concatWith(obs1).subscribe(a -> System.out.println(a));

		System.out.println("--- self ---");
		obs1.concatWith(obs1).concatWith(obs1).subscribe(a -> System.out.println(a));
		
		System.out.println("--- self distinct ---");
		obs1.concatWith(obs1).concatWith(obs1).distinct().subscribe(a -> System.out.println(a));
				
		System.out.println("--- first ---");
		obs1.concatWith(obs2).concatWith(obs3).first().subscribe(a -> System.out.println(a));
	}
	
	public static void test2() {
		System.out.println("--- map ---");
		Observable<Integer> obs1 = Observable.just(1,2,3);
		
		obs1.map(a -> a * 3).subscribe(a -> System.out.println(a));
		
		System.out.println("--- map2 ---");
		Observable<String> obs2 = Observable.just("hello", "hi kim", "good morning world");
		
		obs2.map(a -> a.length()).subscribe(a -> System.out.println(a));
		
	}
	
	public static void test3() {
		// flatMap 은 map과 비슷하면서도 확실히 틀리다.
		System.out.println("--- flatMap1 ---");
		Observable<Integer> obs1 = Observable.just(1,2,3);
		
		obs1.flatMap(a -> Observable.just(a * 3)).subscribe(a -> System.out.println(a));
		
		
		System.out.println("--- flatMap2 ---");
		Observable<String> obs2 = Observable.just("hello", "hi kim", "good morning world");
		
		obs2.flatMap(a -> Observable.just(a.length())).subscribe(a -> System.out.println(a));

		
		System.out.println("--- flatMap3 ---");
		Observable<String> obs3 = Observable.just("hello", "hi kim", "good morning world");
		
		obs2.flatMap(a -> {
			return Observable.from(a.split(" "));
		})
		.subscribe(a -> System.out.println(a));
	}
	
	public static void test4() {
		System.out.println("--- flatMap ---");
		Observable<String> obs2 = Observable.just("hello", "hi kim", "good morning world");
		
		obs2.flatMap(a -> {
			return Observable.from(a.split(" "));
		})
		.subscribe(a -> System.out.println(a));
		
		
		System.out.println("--- concatMap ---");
		Observable<String> obs4 = Observable.just("hello", "hi kim", "good morning world");
		
		obs4.concatMap(a -> {
			return Observable.from(a.split(" "));
		})
		.subscribe(a -> System.out.println(a));
		
	}

	public static void main(String...argc) {
		System.out.println("main start");
		
//		test1();
//		test2();
//		test3();
		test4();
		
	}
}
