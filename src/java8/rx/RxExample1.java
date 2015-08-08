package java8.rx;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Observable : 관측 가능한 값의 흐름을 나타내는 객체 (생산자). ex) 버튼 클릭, 네트웍 인풋, 키보드 클릭 등등..
 *   - subscribe 함수를 통해 구독이 가능하며, 구독자에게 값의 흐름을 알려준다.
 *   
 * Observer : 값의 흐름을 받는 객체 (소비자)
 *   - Observable의 값의 흐름에 따라 onNext, onError, onComplete를 호출받는다.
 *   
 *   subscribeOn() : 어떤 쓰레드에서 수행할지.
 *   observeOn() : 어떤 쓰레드에서 결과를 받을지.
 * 
 * @author nmj
 *
 */
public class RxExample1 {
	
	public static void test1() {
		System.out.println("test1 start");
        Observable<String> obs  = Observable.just("apple", "pear", "blueberry");
        obs.subscribe(
        		// onNext
        		new Action1<String>() {
        			@Override
        			public void call(String fruit) {
        				System.out.println("is next.." + fruit);
        			}
        		},
        		// onError
        		new Action1<Throwable>() {
        			@Override // onError
        			public void call(Throwable t) {
        				System.out.println("throwable.. " + t);
        			}
        		},
        		// onComplete
        		new Action0() {
        			@Override // onComplete
        			public void call() {
        				System.out.println("all complete");
        			}
        		});		
	}

	// user lambda
	public static void test2() {
		System.out.println("test2 start");
        Observable<String> obs  = Observable.just("apple", "pear", "blueberry");
        
        obs.subscribe(
        		// onNext
        		a -> System.out.println("onNext:" + a),
        		// onError
        		a -> {},
        		// onComplete
        		() -> System.out.println("onComplete"));
	}

	// use filter, map
	public static void test3() {
		System.out.println("test3 start");
		Observable<String> obs  = Observable.just("apple", "pear", "blueberry", "strawberry", "banana");
		obs.filter(a -> a.length() > 5)
			.map(a -> a.length())
			.subscribe(
        		// onNext
        		a -> System.out.println("onNext:" + a),
        		// onError
        		a -> {},
        		// onComplete
        		() -> System.out.println("onComplete"));
		
	}

	// test thread
	public static void test4() {
		System.out.println("test4 start");
		Observable<String> obs  = Observable.just("apple", "pear", "blueberry", "strawberry", "banana");
//		obs.subscribeOn(Schedulers.io())			// IO-bound work. test4 end 후에 수행됨.
		obs.subscribeOn(Schedulers.immediate())		// current thread
			.subscribe(
        		// onNext
        		a -> {
           			System.out.println("    onNext:" + a);
        		}
        		,
        		// onError
        		a -> {},
        		// onComplete
        		() -> System.out.println("  onComplete"));
		System.out.println("test4 end");
	}

	// test thread
	public static void test5() {
		System.out.println("test5 start -1 " + Thread.currentThread().getName());
		Observable<String> obs  = Observable.just("aaa", "bbb");
			obs.subscribe(
	    		a -> System.out.println("    onNext:" + a + " - " + Thread.currentThread().getName()),
	    		a -> {},
	    		() -> System.out.println("  onComplete - " + Thread.currentThread().getName()));
			
		Observable<String> obs2  = Observable.just("aaa", "bbb");
			obs2.subscribeOn(Schedulers.immediate())
				.observeOn(Schedulers.io())
				.subscribe(
	    		a -> System.out.println("    onNext:" + a + " - " + Thread.currentThread().getName()),
	    		a -> {},
	    		() -> System.out.println("  onComplete - " + Thread.currentThread().getName()));

		System.out.println("test5 end - " + Thread.currentThread().getName());
		
	}

	// exception
	public static void test6() {
		Observable<String> obs  = Observable.just("apple", "pear", "blueberry", "strawberry", "banana");
		obs.subscribe(a -> {
			System.out.println("onNext : " + a);
			if ( a.length() > 5 ) {
				throw new RuntimeException();
//				throw new Exception();				// 이건 안되네.
			}
		}, 
		a -> {
			System.out.println("Exception!!! : " + a);
		}, 
		() -> System.out.println("onComplete"));
	}
	
	// get count
	public static void test10() {
		Observable<String> obs  = Observable.just("apple", "pear", "blueberry", "strawberry", "banana");
		obs.count().subscribe(a -> System.out.println("cnt : " + a), 
				a -> {}, 
				() -> {});
	}
	
	public static void main(String... names){
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
		test6();
		
	}
}
