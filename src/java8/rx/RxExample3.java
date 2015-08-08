package java8.rx;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 
 * @author nmj
 *
 */
public class RxExample3 {
	
	public static void printHello() {
		System.out.println("print..");
	}
	
	public static void printHello1(String msg) {
		System.out.println("print..:" + msg);
	}
	
	public static void test1() {
		Observable<Action0> obs1 = Observable.timer(2, TimeUnit.SECONDS)
				.map(a -> RxExample3::printHello);
		obs1.subscribe(b -> b.call());		
	}
	
	public static void test2() {
		Observable<Action1<String>> obs1 = Observable.timer(2, TimeUnit.SECONDS)
				.map(a -> RxExample3::printHello1);
		obs1.subscribe(b -> b.call(" hi.. "));		
	}

	public static void test3() {
		Observable<Action0> obs1 = Observable.timer(2, TimeUnit.SECONDS)
				.map(a -> () -> {
					System.out.println("!!print!!..");
				});
		obs1.subscribe(b -> b.call());		
	}
	
	public static void test4() {
		Observable<Action1<String>> obs1 = Observable.timer(2, TimeUnit.SECONDS)
				.map(a -> str -> {
					System.out.println("!!print!!..:" + str);
				});
		obs1.subscribe(b -> b.call("!!hi!!"));		
	}
	
	public static void main(String... argc) {
		System.out.println("main start");
		
//		test1();
//		test2();
//		test3();
		test4();
		
		
		
		for ( int i=0; i<100; ++i ) {	// main Thread가 바로 종료되어버리면 프로세스가 종료되어버려서 테스트가 안됨.
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
