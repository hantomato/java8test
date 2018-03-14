package java8.book;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 *
 * Created by neowiztomato on 2018-01-22.
 */
public class NmjChapter6 {
    public static void main(String... argc) {
        System.out.println("== main start ==========");
//        test1();
//        test2();
//        test3();
        exercise3();
        exercise9();
    }

    public static void test1() {
        AtomicLong nextNumber = new AtomicLong();
        nextNumber.incrementAndGet();

        nextNumber.getAndUpdate(x -> x);

        LongAdder adder = new LongAdder();
        adder.increment();
    }

    public static void test2() {

    }

    public static void test3() {

    }

    private static AtomicLong atomicLong = new AtomicLong();
    private static Instant atomicLongStart;

    private static LongAdder longAdder = new LongAdder();
    private static Instant longAdderStart;

    public static void exercise3() {
        System.out.println("===== exercise 3 =====");
        // 각각 카운터를 100,000번 증가시키는 스레드 1,000개를 생성하다.
        // 각각 AtomicLong과 LongAdder를 사용할 때의 성능을 비교하라

        // test 1
//        MyThread[] thdArray = new MyThread[1000];
//        for (int i=0; i<1000; ++i) {
//            thdArray[i] = new MyThread();
//        }
//
//        atomicLongStart = Instant.now();
//        for (int i=0; i<1000; ++i) {
//            thdArray[i].start();
//        }


        // test 2
//        MyThread2[] thdArray = new MyThread2[1000];
//        for (int i=0; i<1000; ++i) {
//            thdArray[i] = new MyThread2();
//        }
//
//        longAdderStart = Instant.now();
//        for (int i=0; i<1000; ++i) {
//            thdArray[i].start();
//        }

        // atomicLong 작업 걸린 시간은 1900~1950 임
        // longAdder 작업 걸린 시간은 320 임
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i=0; i<100000; ++i) {
                atomicLong.incrementAndGet();
            }
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(atomicLongStart, end);
            System.out.println("time : " + timeElapsed.toMillis());
        }
    }

    public static class MyThread2 extends Thread {
        @Override
        public void run() {
            for (int i=0; i<100000; ++i) {
                longAdder.increment();
            }
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(longAdderStart, end);
            System.out.println("time : " + timeElapsed.toMillis());
        }
    }

    public static void exercise9() {
        System.out.println("===== exercise 9 =====");
        // 피보나치 수열 문제..
        Matrix[] array = new Matrix[10];
        final int[][] f = {{1, 1}, {1, 0}};
        Arrays.parallelSetAll(array, i -> new Matrix(f));       // 초기값 세팅
        Arrays.parallelPrefix(array, (m1, m2) -> m1.multiply(m2));
        for (int i=0; i<10; ++i) {
            System.out.println(i + " : " + array[i].m[0][0]);
        }
        // 1, 2, 3, 5, 8, 13, 21, 34

    }

    public static class Matrix {
        int[][] m;

        Matrix(int[][] m) {
            this.m = m;
        }

        // 행렬 곱하기
        Matrix multiply(Matrix other) {
            int x1 = m[0][0] * other.m[0][0] + m[0][1] * other.m[1][0];
            int y1 = m[0][0] * other.m[0][1] + m[0][1] * other.m[1][1];
            int x2 = m[1][0] * other.m[0][0] + m[1][1] * other.m[1][0];
            int y2 = m[1][0] * other.m[0][1] + m[1][1] * other.m[1][1];
            return new Matrix(new int[][]{{x1, y1}, {x2, y2}});
        }
    }

}
