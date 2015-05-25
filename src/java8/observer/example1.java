package java8.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * @author tomatomb
 * 
 * Observer Pattern.
 * 
 * subject는 registerObserver, removeObserver, notifyObserver API를 제공해야한다.
 * 자바의 Subject(=observable)은 위에 해당하는 3개의 API를 제공한다. 
 *   - addObserver (Observer observer)
 *   - deleteObserver (Observer observer) 
 *   - notifyObservers (Object data)
 *   
 * 자바의 observer는 update API를 제공한다.
 *   - update(Observable observable, Object data) 
 * 
 * Observer Pattern이란?
 * 상태를 갖고 있는 Subject와 그에 의존하는 여러 Observer들이 있고,
 * Subject의 상태가 바뀌면 연결된 모든 Observer들에게 notify를 한다.
 *   - Subject와 Observer는 1:N 관계
 *   - Loose Coupling : 새로운 Observer가 추가되더라도 전체에 영향이 적다.
 *   - Subject와 Observer는 서로 독립적이다.
 * 
 * 참고자료
 * http://warmz.tistory.com/751
 * 
 */
public class example1 {
	
	public static void main(String[] args) {
		
        out.println("Enter Text >");
        EventSource eventSource = new EventSource();	// This is Subject ( = Manager managing all observer)

        
        
		class firstObserver implements Observer {		// 1번 옵저버
			@Override
			public void update(Observable o, Object arg) {
				out.println("\n나는 1번 옵져버. 공지사항 확인함.: " + arg);
			}
		}
	
		// 인스턴스 생성해서 옵저버로 들록
        firstObserver firstObj = new firstObserver();
        eventSource.addObserver(firstObj);			
       
        // Anonymous class를 옵저버로 등록
        eventSource.addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				out.println("\n나는 2번 옵져버. 공지사항 확인함.: " + arg);
			}
        });

        // lambda 사용. (Observer가 method 1개만 갖는 interface 라서 아래 문법이 가능한듯..)
        eventSource.addObserver((Observable obj, Object arg) -> {
        	out.println("\n나는 3번 옵져버. 공지사항 확인함.: " + arg);	 
        });
        eventSource.addObserver((Observable obj, Object arg) -> { 
        	out.println("\n나는 4번 옵져버. 공지사항 확인함.: " + arg);
        });

        
         
        new Thread(eventSource).start();
        
	}
	

	// 
	public static class EventSource extends Observable implements Runnable {

		@Override
		public void run() {
	        while (true) {
	            out.println("공지사항을 입력하세요.. ");
	            String response = new Scanner(System.in).next();
	            out.println("공지 뜸");
	            setChanged();
	            out.println("모든 observer 들에게 notify 하자.");
	            notifyObservers(response);
	            out.println("notify 완료..");
	            out.println(" ");
	        }
		}
		
	}
}
