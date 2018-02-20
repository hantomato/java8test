package java8.book;

import com.sun.media.jfxmedia.logging.Logger;
import java8.book.util.ColorTransformer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.*;
import java.util.logging.Level;
import java.util.stream.Stream;

/**
 * 꼭 풀어야할 문제는 1, 7, 13, 19번 문제
 */
//public class NmjChapter3 extends Application {
public class NmjChapter3 {
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//    }

    public static void main(String... argc) {
        System.out.println("== main start ==========");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
        exercise7();
        exercise19();
    }

    public static int[] a = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // 필수
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

//        int param = 10;
//        assert(param > 100);
//        myAssert(param > 100);
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
        // How many functional interfaces with Filter in their name can you find in the
        //Java API? Which ones add value over Predicate<T>?
        // 이름에 Filter를 포함하는 함수형 인터페이스는 없다.
    }

    public static void exercise5() {
        System.out.println("===== exercise 5 =====");

    }

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x, y)));
        return out;
    }

    public static Image transformNew(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y,
                        f.apply(x, y, in.getPixelReader().getColor(x, y))); // 이 부분만 변경함
        return out;
    }
    public static void exercise6() {
        System.out.println("===== exercise 6 =====");

    }

    public static int COMP_REVERSE       = 1;
    public static int COMP_IGNORE_CASE   = 2;
    public static int COMP_IGNORE_SPACE  = 4;

    // 필수
    public static void exercise7() {
        System.out.println("===== exercise 7 =====");
        // Comparator<String>을 생성하는 메서드를 작성하라.
        String[] array1 = new String[] {"hello", "world", "mon", "tue", "WEN", "THU", "aa", "cc", "bb"};
        String[] array2 = new String[] {"hello", "world", "m on", "tue", "WEN", "TH U", "aa", "c c", "bb"};
        String[] array3 = new String[] {"hello", "world", "mon", "tue", "WEN", "THU", "aa", "cc", "bb"};
        Arrays.sort(array1, ComparatorGenerator(0));
        System.out.println(Arrays.deepToString(array1));
        Arrays.sort(array2, ComparatorGenerator(COMP_IGNORE_CASE));
        System.out.println(Arrays.deepToString(array2));
        Arrays.sort(array3, ComparatorGenerator(COMP_IGNORE_CASE|COMP_IGNORE_SPACE));
        System.out.println(Arrays.deepToString(array3));

    }

    public static Comparator<String> ComparatorGenerator (int compareOption) {
        return (String t1, String t2) -> {
            if ((compareOption & COMP_REVERSE) == COMP_REVERSE) {
                // 생략
            }
            if ((compareOption & COMP_IGNORE_CASE) == COMP_IGNORE_CASE) {
                t1 = t1.toUpperCase();
                t2 = t2.toUpperCase();
            }
            if ((compareOption & COMP_IGNORE_SPACE) == COMP_IGNORE_SPACE) {
                t1 = t1.replace(" ", "");
                t2 = t2.replace(" ", "");
            }
            return t1.compareTo(t2);
        };
    }

    // 필수
    public static void exercise13() {
        System.out.println("===== exercise 13 =====");

    }


    // 필수
    public static void exercise19() {
        System.out.println("===== exercise 19 =====");
        //    <U> U reduce(U identity,
        //                 BiFunction<U, ? super T, U> accumulator,
        //                 BinaryOperator<U> combiner);
        // 두번째 파라미터에서 BiFunction 의 첫번째 타입 U 를 ? super U 로 선언해야하는가?

        Stream<String> src = Stream.of("a", "b", "c", "d", "e");

        // reduce : Optional<T> reduce(BinaryOperator<T> accumulator);
        System.out.println(Stream.of("a", "b", "c", "d").reduce((x,y) -> x + " " + y).toString());
        System.out.println(Stream.of("a", "b", "c", "d").reduce((x,y) -> y + " " + x).toString());   // 역순

        // reduce : T reduce(T identity, BinaryOperator<T> accumulator);
        System.out.println(Stream.of("a", "b", "c", "d").reduce("first", (String x, String y) -> x + " " + y).toString());

        // reduce : <U> U reduce(U identity,
        //                BiFunction<U, ? super T, U> accumulator,
        //                BinaryOperator<U> combiner);
        //
        // T : 스트림의 아이템 type
        // U : 리턴 타입

        System.out.println(Stream.of("a", "b", "c", "d").parallel().reduce(10,
                (Integer x, String y) -> {
//                    return x + y.length();
                    return 10;
                    },
                (Integer x, Integer y) -> {
                    return x + y;}
                    ).toString());

        // generic wildcard
        // ? : 모든 타입 다 올 수 있다.
        // ? super T : T 의 상위 타입만 올 수 있다.
        // ? extend T : T 의 하위 타입만 올 수 있다.

    }

    public static<T, U> U reduce1(U identity,
                                  BiFunction<U, ? super T, U> accumulator,
                                  BinaryOperator<U> combiner) {


        return identity;
    }

    public static<T, U> U reduce2(U identity,
                                  BiFunction<? super U, ? super T, U> accumulator,
                                  BinaryOperator<U> combiner) {

        return identity;
    }

}
