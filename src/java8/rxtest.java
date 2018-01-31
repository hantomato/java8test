package java8;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;

public class rxtest {


	public static void main(String... argc) {
		System.out.println("start main!");
		Observable<String> obs  = Observable.just("apple");
		Observable<String> aaa  = Observable.just("apple", "bbb");
		Observable<String> bbb  = Observable.just("aa", "bb", "cc");
		
		
		Observable<Integer> cnt = bbb.count();
		
		
		
		bbb.subscribe(new Action1<String>() {

			@Override
			public void call(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("aaa 1 : " + arg0);
			
			}
			
		}, new Action1<Throwable>() {

			@Override
			public void call(Throwable arg0) {
				// TODO Auto-generated method stub
				System.out.println("aaa 2");
			}
			
		}, new Action0() {

			@Override
			public void call() {
				// TODO Auto-generated method stub
				System.out.println("aaa 3");
			}
			
		});
		

//		obs.subscribe(
//				// onNext
//				new Action1<String>() {
//					@Override
//					public void call(String fruit) {
//						System.out.println("aaa");
//						;
////							throw();
//					}
//				},
//				// onError
//				new Action1<Throwable>() {
//					@Override // onError
//					public void call(Throwable t) {
//						System.out.println("aaa 2");
//					}
//				},
//				// onComplete
//				new Action0() {
//					@Override // onComplete
//					public void call() {
//						System.out.println("aaa 3");
//					}
//				});
	}
}
