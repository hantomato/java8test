package java8.rx;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Creating Observables

 * http://reactivex.io/documentation/operators.html
 * 
	Create — create an Observable from scratch by calling observer methods programmatically
	Defer — do not create the Observable until the observer subscribes, and create a fresh Observable for each observer
	Empty/Never/Throw — create Observables that have very precise and limited behavior
	From — convert some other object or data structure into an Observable
	Interval — create an Observable that emits a sequence of integers spaced by a particular time interval
	Just — convert an object or a set of objects into an Observable that emits that or those objects
	Range — create an Observable that emits a range of sequential integers
	Repeat — create an Observable that emits a particular item or sequence of items repeatedly
	Start — create an Observable that emits the return value of a function
	Timer — create an Observable that emits a single item after a given delay
 *  
 *  
 * @author nmj
 *
 */
public class RxExample2 {

	
	public static void testMerge() {
        Observable<String> obs1  = Observable.just("apple", "pear", "blueberry");
        Observable<String> obs2  = Observable.just("apple1", "pear2", "blueberry3");
        
        Observable<String> obs3 = Observable.merge(obs1, obs2);
        
        obs3.subscribe(
        	a -> System.out.println("onNext : " + a),
        	a -> {},
        	() -> System.out.println("onComplete"));
	
	}

	
	public static void testFrom() {
		Observable<String> obs1 = Observable.from(new String[] {"aaa", "bbb"});
        obs1.subscribe(
            	a -> System.out.println("    onNext : " + a),
            	a -> {},
            	() -> System.out.println("  onComplete"));
	}
	
	public static void testTimer() {
		System.out.println("start");
		Observable<Long> obs1 = Observable.timer(3, TimeUnit.SECONDS);
		obs1.observeOn(Schedulers.io())
			.subscribeOn(Schedulers.io())
        	.subscribe(
            	a -> System.out.println("    onNext : " + a + " - " + Thread.currentThread().getName()),
            	a -> {},
            	() -> System.out.println("  onComplete"));
		System.out.println("end");
	}
	
	public static void testInterval() {
		System.out.println("start");
		Observable<Long> obs1 = Observable.interval(1, TimeUnit.SECONDS);
        obs1.subscribe(
            	a -> System.out.println("    onNext : " + a + " - " + Thread.currentThread().getName()),
            	a -> {},
            	() -> System.out.println("  onComplete"));
		System.out.println("end");
	}
	
	public static void testCreate() {
//		public static interface Observable.OnSubscribe<T>
//		extends Action1<Subscriber<? super T>>		

		Observable<String> obs1 = Observable.create(a -> {
			a.onNext("[first onNext data]");
			a.onNext("[second onNext data]");
			a.onCompleted();
			
			}
		);

		obs1.subscribe(
            	a -> System.out.println("    .. onNext : " + a + " - " + Thread.currentThread().getName()),
            	a -> {},
            	() -> System.out.println("  .. onComplete"));
		

		Observable.create(a -> {
			a.onNext(123);
			a.onNext("hello world");
			a.onNext(3.14);
			a.onCompleted();
			}
		).subscribe(
        	a -> System.out.println("    .. onNext : " + a + " - " + Thread.currentThread().getName()),
        	a -> {},
        	() -> System.out.println("  .. onComplete"));
		
	}
	
	public static void test1() {
		
	}
	
	public static void main(String... argc) {
		System.out.println("main start");
//		testMerge();
//		testFrom();
		testCreate();
		
//		Thread thd = new Thread(() -> {
////			testTimer();
//			testInterval();
//		});
//		thd.start();
//		
//		
//		for ( int i=0; i<10; ++i ) {	// main Thread가 바로 종료되어버리면 프로세스가 종료되어버려서 테스트가 안됨.
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println("main end");
	}
}
