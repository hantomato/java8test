package java8.book;

import com.sun.media.jfxmedia.logging.Logger;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.logging.Level;

public class NmjChapter3 {
    public static void main(String... argc) {
        System.out.println("== main start ==========");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
        exercise7();
        exercise8();
        exercise9();
        exercise10();
        exercise11();
        exercise12();
        exercise13();
    }

    public static String[] az = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
    public static int[] a = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    public static void exercise1() {
        System.out.println("===== exercise 1 =====");
        // logIf 메서드를 구현하라는 문제. logIf 메서드의 시그니처도 정의해야 함.
        for (int i : a) {
            logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
        }
    }

    /**
     *
     * @param logLevel log level
     * @param cond 로그를 출력할지 말지에 대한 조건
     * @param getMsg 출력해야 할 로그 메세지
     */
    public static void logIf(Level logLevel, Supplier<Boolean> cond, Supplier<String> getMsg) {
        if (cond.get()) {
            Logger.logMsg(logLevel.intValue(), getMsg.get());
        }
    }

    public static void exercise2() {
        System.out.println("===== exercise 2 =====");
        // withLock 을 구현하라. 메서드 시그니처는 주어짐
        ReentrantLock myLock = new ReentrantLock();
        withLock(myLock, () -> System.out.println("exercise2"));
    }

    public static void withLock(ReentrantLock lock, Runnable s) {
        lock.lock();
        try {
            s.run();
        } finally {
            lock.unlock();
        }
    }

    public static void exercise3() {
        System.out.println("===== exercise 3 =====");
        // java 1.4의 assert 같은 것을 구현해보라.
        // assert example..
        // assert (param == true);
        // assert (a > 10)

        int param = 10;
        assert(param > 100);
        myAssert(param > 100);
    }

    public static void myAssert(boolean cond) {
        if (!cond) {
            throw new AssertionError("myAssert error");
        }
    }

    public static void _exercise3(int a) {
        System.out.println("aaa");
        assert(false);
    }

    public static void exercise4() {
        System.out.println("===== exercise 4 =====");
        // 이름에 Filter를 포함하는 함수형 인터페이스는 없다.

    }

    public static void exercise5() {
        System.out.println("===== exercise 5 =====");

    }

    public static void exercise6() {
        System.out.println("===== exercise 6 =====");

    }

    public static void exercise7() {
        System.out.println("===== exercise 7 =====");

    }

    public static void exercise8() {
        System.out.println("===== exercise 8 =====");

    }

    public static void exercise9() {
        System.out.println("===== exercise 9 =====");

    }

    public static void exercise10() {
        System.out.println("===== exercise 10 =====");

    }

    public static void exercise11() {
        System.out.println("===== exercise 1 =====");

    }

    public static void exercise12() {
        System.out.println("===== exercise 12 =====");

    }

    public static void exercise13() {
        System.out.println("===== exercise 13 =====");

    }

}
