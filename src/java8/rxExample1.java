package java8;

import rx.Observable;

/**
 * rx. ReactiveX
 * http://reactivex.io
 * 
 * API reference
 * http://reactivex.io/RxJava/javadoc/ 
 * 
 * 일반적인 코딩 스타일
 * observable
 * .subscribeOn(background)			// 코드가 수행될 쓰레드 지정
 * .filter(...))					// 필터링
 * .map(...)						// 데이터 변환
 * .doOnError(...)					// 에러 처리
 * .observeOn(mainThread)			// 결과를 받을 쓰레드 지정
 * .subscribe() -> subscription		// 작업 시작
 * 
 * 
 * subscription.unsubscribe()		// 작업 중간에 작업 취소 가능
 * 
 * @author tomatomb
 *
 */
public class rxExample1 {

	public static void main(String... argc) {
		Observable<String> obs  = Observable.just("aaa", "bbb", "ccc");
	}
}
