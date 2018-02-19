package java8.book;

import java.util.*;
import java.util.stream.Collectors;
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
        exercise9();
        exercise10();
        exercise11();
        exercise12();
        exercise13();
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

    }

    public static void test2() {
        // 1+2+3+4 .. +10
        start = 1;
        int sum = IntStream.generate(() ->
        {
            return start++;
        })
                .limit(10)
                .peek(x -> System.out.println("peek : " + x))
                .sum();
        System.out.println("sum:" + sum);

        Stream<String> words = Stream.of("1111", "33333", "999", "44", "5555", "66666", "777","22", "8" );
//        words.sorted()
        words.sorted(Comparator.comparing(String::length).reversed())
                .peek(x -> System.out.println("peek2 : " + x))
                .count();

        Stream.of(1,2,3,4,5).reduce((x,y) -> x+y);

        Stream<Integer> src1 = Stream.of(10, 20, 30, 40);
        Iterator<Integer> iter = src1.iterator();
        Integer[] temp7 = (Integer[])src1.toArray();
        while (iter.hasNext()) {
            System.out.println("iter : " + iter.next());
        }

        Stream<Integer> src2 = Stream.of(10, 20, 30, 40);
        IntSummaryStatistics ints = src2.collect(Collectors.summarizingInt(x -> x));
        double dd = ints.getAverage();
        long ll = ints.getCount();
        int ii = ints.getMin();
        int iii = ints.getMax();
        long lll = ints.getSum();

        src2.forEach(x -> System.out.println(x));
        src2.forEach(System.out::println);

    }

    public static void exercise1() {
        // 41 페이지에 있는 "2.1 반복에서 스트림 연산으로" 절의 for 루프를 병렬 버전으로 작성하라. 먼저 프로세서의 개수를 얻는다.
        // 다수의 스레드를 생성해 각 스레드가 리스트의 각 세그먼트를 대상으로 작업하게 하고, 결과들이 나오면 합산한다.
        // (여러분은 스레드들이 단일 카운터를 업데이트하는 상황을 원하지 않을 것이다. 그 이유는 무엇인가?)
        System.out.println("===== exercise 1 =====");

    }

    public static void exercise2() {
        System.out.println("===== exercise 2 =====");
        // 처음 5개 긴 단어를 요청했을 때 일단 긴 단어를 5번째 발견하고 나면 filter 메서드를 호출하지 않음을 확인하라.
        // 간단하게 각 메서드 호출을 기록하라.
    }

    public static void exercise3() {
        // stream 대신 parallelStream을 이용해 긴 단어 개수를 셀 때 차이를 측정하라. 각 호출 이전과 이후에
        // System.currentTimeMillis를 호출하고 차이를 출력한다. 빠른 컴퓨터를 보유하고 있다면 전쟁과 평화 War and Peace처럼 더 큰 문서로 바꿔서 측정한다
        System.out.println("===== exercise 3 =====");

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

    public static void exercise4() {
        // int[] values = { 1, 4, 9, 16 } 배열이 있다고 하자. Stream.of(values)의 결과는 무엇인가? int 스트림은 어떻게 얻을 수 있는가?
        // Arrays.stream 사용하면 IntStream을 얻을 수 있다.
        System.out.println("===== exercise 4 =====");
        int[] values = { 1, 4, 9, 16 };
//        Stream<int> temp1 = Stream.of(values);      // error
        Stream<int[]> temp2 = Stream.of(values);
        temp2.forEach(x -> System.out.println(x));      // 하나의 element

        Integer[] values3 = { 3, 4, 5, 6 };
        Stream<Integer> temp3 = Stream.of(values3);
        temp3.forEach(x -> System.out.println(x));      // 4개의 element

        String[] array = {"a", "b", "c", "d"};
        Stream<String> stream2 = Stream.of(array);
        stream2.forEach(x -> System.out.println(x));

        IntStream temp5 = Arrays.stream(values);
        temp5.forEach(x -> System.out.println(x));

    }

    public static void exercise5() {
        // Stream.iterate를 사용해 난수의 무한 스트림을 만들어라. 이때 Math.random을 호출하지 말고
        // 선형 적합 발생기 linear congruential generator를 직접 구현해서 사용한다.
        // 이와 같은 발생기에서는 x0 = seed 로 시작해 적합한 a, c, m 값에 대해 xn + 1 = (axn + c) % m을 생산한다.
        // 파라미터로 a, c, m, seed를 받고 Stream<Long>을 리턴하는 메서드를 구현해야 한다.
        // a = 25214903917, c = 11, m = 248 값으로 난수의 무한 스트림을 만들어본다.
        System.out.println("===== exercise 5 =====");


    }

    public static void exercise6() {
        // “2.3 filter, map, flatMap 메서드” 절의 characterStream 메서드는 먼저 ArrayList를 채운 후 스트림으로
        // 변환하기 때문에 약간은 세련되지 못했다. 스트림에 기반을 둔 한 행짜리 메서드로 작성하라.
        // 한 가지 접근법은 0 ~ s.length() - 1 범위에서 정수 스트림을 만들어서 메서드 레퍼런스에 맵핑하는 것이다.
        System.out.println("===== exercise 6 =====");


        Stream<Character> ret = _exercise6("hello");
        ret.peek(System.out::println).count();
    }

    public static Stream<Character> _exercise6(String sss) {
        return Stream.iterate(0, (x) -> x+1)
                .limit(sss.length())
                .map(x -> sss.charAt(x));
    }

    public static void exercise7() {
        // 여러분의 관리자가 public static <T> boolean isFinite(Stream<T> stream) 메서드를 작성하라고 했다고 하자.
        // 이 메서드를 작성하는 일이 썩 좋지 못한 생각인 이유는 무엇인가? 어쨌든 지금 바로 작성하자.
        System.out.println("===== exercise 7 =====");

    }

    public static <T> boolean isFinite(Stream<T> stream) {
        try {
            long cnt = stream.count();
        } catch (Throwable  e) {
            return false;
        }
        return true;
    }

    public static void exercise8() {
        // public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) 메서드를 작성하라.
        // 이 메서드는 first와 second 스트림의 요소들을 교환하며, 두 스트림 중 하나에서 요소가 바닥이 나면 작업을 중단한다.
        // => 문제가 이해 안된다.
        System.out.println("===== exercise 8 =====");


    }

    public static void exercise9() {
        // Stream<ArrayList<T>>에 있는 모든 요소들을 ArrayList<T> 하나로 결합하라. reduce의 세 가지 형태를 이용해
        // 이 작업을 수행하는 방법을 보여라.
        System.out.println("===== exercise 9 =====");

        ArrayList<Integer> src1 = new ArrayList<>(Arrays.asList(1,2,3,4));
        ArrayList<Integer> src2 = new ArrayList<>(Arrays.asList(5,6,7,8));

        Stream<ArrayList<Integer>> src3 = Stream.of(src1, src2);
        Optional<ArrayList<Integer>> ret = src3.reduce((x,y) -> {x.addAll(y); return x;});
        System.out.println("ret : " + (ret.isPresent() ? ret.get() : "null"));


    }

    public static void exercise10() {
        // Stream<Double>의 평균을 계산하는 데 사용할 수 있는 reduce 호출을 작성하라. 단순히 합계를 계산해 count()로
        // 나눌 수 없는 이유는 무엇인가?
        // => reduce를 사용해야만 합계 또는 갯수를 구할수 있는데. reduce는 스트림에서 한번만 사용할 수 있다.
        // => 결합법칙에 위배되기때문에 recude로는 평균을 구할 수 없다.
        System.out.println("===== exercise 10 =====");

    }

    public static void exercise11() {
        // 단일 ArrayList를 스트림의 크기로 생성했다면, 여러 ArrayList를 병합하는 대신 단일 리스트 안에 스트림 결과들을
        // 동시에 모을 수 있어야 한다. 이는 떨어진 위치에서 병행 set 연산은 스레드에 안전하기 때문이다. 이 작업을 어떻게 할 수 있는가?
        System.out.println("===== exercise 11 =====");

    }

    public static void exercise12() {
        // “2.13 병렬 스트림” 절에서 설명한 것처럼 AtomicInteger 배열을 업데이트하는 방법으로 병렬 Stream<String>에 있는
        // 모든 짧은 단어의 개수를 세라. 각 카운터를 안전하게 증가시키기 위해 원자적 메서드인 getAndIncrement를 사용한다.
        System.out.println("===== exercise 12 =====");

    }

    public static void exercise13() {
        // 연습문제 12를 다시 풀되, 이번에는 짧은 문자열을 걸러내고 collect 메서드를 Collectors.groupingBy, Collectors.counting과 조합해 사용하라.
        System.out.println("===== exercise 12 =====");

    }
}
