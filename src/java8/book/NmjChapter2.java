package java8.book;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java stream 중요 포인트
 * * stream 개념 이해하기
 * * 코딩 스타일에 익숙해지기
 * * 코딩 가이드를 지키기
 *
 * 중급자가 되기 위한 조건
 * * 대부분의 기능들을 사용해보기
 * * 람다 메서드 규약을 보고 기능 이해하기
 * * 쓰레드 이해하기
 * * 예외처리 완벽하게 이해하기
 *
 * Created by neowiztomato on 2018-01-30.
 */
public class NmjChapter2 {
	
    public static int start = 1;

    public static void main(String... argc) {
        System.out.println("== main start ==========");
//        test1();
        test2();
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
        exercise7();
        exercise8();
    }

    public static void test1() {
        //////////
        Stream<String> words = Stream.of("1111", "22", "33333", "44", "5555", "66666", "777", "8", "999");
        long cnt = words.parallel()
                .filter(x -> {
                    System.out.println("thd : " + Thread.currentThread().getName());
                    return x.length() > 2;
                })
                .count();
        System.out.println("count : " + cnt);


        //////////
        {
            System.out.println("single job start");
            long start = System.currentTimeMillis();
            int sum = IntStream.iterate(1, x -> x + 1)
                    .limit(10000)
                    .filter(x -> {
                        return (x % 3) == 0;
                    })
                    .map(x -> x * 2)
                    .sum();
            long end = System.currentTimeMillis();
            System.out.println("sum:" + sum + ", single time  : " + (end - start));
        }

        {
            System.out.println("parallel job start");
            long start = System.currentTimeMillis();
            int sum = IntStream.iterate(1, x -> x + 1)
                    .parallel()
                    .limit(10000)
                    .filter(x -> {
//                        System.out.println("thd : " + Thread.currentThread().getName());
                        return (x % 3) == 0;
                    })
                    .map(x -> x * 2)
                    .sum();
            long end = System.currentTimeMillis();
            System.out.println("sum:" + sum + ", parallel time  : " + (end - start));
        }
    }

    public static void test2() {
        // 1+2+3+4 .. +10
        start = 1;
        int sum = IntStream.generate(() ->
                    {
                        return start++;
                    })
                .limit(10)
                .sum();
        System.out.println("sum:" + sum);
    }

    public static void exercise1() {
        System.out.println("===== exercise 1 =====");

    }

    public static void exercise2() {
        System.out.println("===== exercise 2 =====");

    }

    public static void exercise3() {
        System.out.println("===== exercise 3 =====");

    }

    public static void exercise4() {
        System.out.println("===== exercise 4 =====");

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

}
