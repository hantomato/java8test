package java8.book;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

/**
 *
 *
 * Created by neowiztomato on 2018-01-22.
 */
public class NmjChapter4{
    public static void main(String... argc) {
        System.out.println("== main start ==========");
//        test0();
//        test1();
        test2();
//        test3();
//        exercise1();
//        exercise2();
//        exercise3();
//        exercise4();
//        exercise5();
//        exercise6();
//        exercise7();
//        exercise8();
    }

    public static void test0() {
        LocalDate today = LocalDate.of(2018, 3, 5);
        System.out.println("test LocalDate");
        System.out.println("today : " + today);                     // 2018-03-05
        System.out.println("temp1 : " + today.plusDays(3));         // 2018-03-08. 3일 더한 날
        System.out.println("temp1 : " + today.plusDays(30));        // 2018-04-04. 30일 더한 날
        System.out.println("temp1 : " + today.minusDays(3));        // 2018-03-02. 3일 뺀 날
        System.out.println("temp1 : " + today.minusDays(10));       // 2018-02-23. 10일 뺀 날
        System.out.println("temp2 : " + today.plusWeeks(1));    // 2018-03-12. 7일 더한 날
        System.out.println("temp2 : " + today.plusWeeks(6));    // 2018-04-16. 42일 더한 날
        System.out.println("temp2 : " + today.plusDays(6*7));   // 2018-04-16. 상동
        System.out.println("temp2 : " + today.minusWeeks(1));   // 2018-02-26. 7일 뺀 날
        System.out.println("temp2 : " + today.minusWeeks(6));   // 2018-01-22. 42일 뺀 날
        System.out.println("temp2 : " + today.minusDays(6*7));   // 2018-01-22. 상동

        System.out.println("temp3 : " + today.withDayOfMonth(1));    // 2018-03-01. 그 달의 1번째 날
        System.out.println("temp3 : " + today.withDayOfMonth(25));   // 2018-03-25. 그 달의 25번째 날
        System.out.println("temp3 : " + today.withDayOfYear(5));     // 2018-01-05. 그 해의 5번째 날
        System.out.println("temp3 : " + today.withDayOfYear(40));    // 2018-02-09. 그 해의 40번째 날
        System.out.println("temp3 : " + today.withMonth(1));        // 2018-01-05. 달을 1로 바꾼다.
        System.out.println("temp3 : " + today.withMonth(5));        // 2018-05-05. 달을 5로 바꾼다.
        //System.out.println("temp3 : " + today.withMonth(15));       // java.time.DateTimeException: Invalid value for MonthOfYear (valid values 1 - 12): 15
        System.out.println("temp3 : " + today.withYear(1));         // 0001-03-05. 년도를 1로 바꾼다.
        System.out.println("temp3 : " + today.withYear(100));       // 0100-03-05. 년도를 100으로 바꾼다.
        System.out.println("temp3 : " + today.withYear(2000));       // 2000-03-05. 년도를 2000으로 바꾼다.

        System.out.println("temp4 : " + today.getDayOfMonth());     // 5. 그 달의 몇번째 날인가. (1 ~ 31)
        System.out.println("temp4 : " + today.getDayOfWeek());      // MONDAY. 그 주의 몇번째 날인가. (DayOfWeek 열거값)
        System.out.println("temp4 : " + today.getDayOfYear());      // 64. 그 해의 몇번째 날인가. (1 ~ 366)
        System.out.println("temp4 : " + today.getMonth());          // MARCH. 달 구하기
        System.out.println("temp4 : " + today.getMonthValue());     // 3. 달 구하기
        System.out.println("temp4 : " + today.getYear());           // 2018. 년도 구하기

        LocalDate tomorrow = LocalDate.of(2018, 3, 6);
        System.out.println("temp5 : " + today.isAfter(tomorrow));   // false
        System.out.println("temp5 : " + today.isBefore(tomorrow));  // true
        System.out.println("temp5 : " + tomorrow.isAfter(today));   // true
        System.out.println("temp5 : " + tomorrow.isBefore(today));  // false
        System.out.println("temp5 : " + today.until(today.plusWeeks(1)).getDays());     // 7. 두 날짜 사이의서 Period 또는 ChronoUnit 수를 구한다.

        // today.plus(), minus() // Duration 또는 Period를 더하거나 뺀다.

    }

    public static void test1() {
        LocalDate today = LocalDate.of(2018, 3, 5);
        System.out.println("test TemporalAdjusters. '매월 첫번째 화요일' 같은 날짜를 구한다.");     // 이건 FunctionalInterface 이다.
        System.out.println("today : " + today);                     // 2018-03-05. 월요일임.

        LocalDate firstTuesday =  today.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println("firstTuesday : " + firstTuesday);   // 2018-03-06
        System.out.println("temp1 : " + today.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));          // 2018-03-12. 다음의 월요일 구하기 (3월 5일이 월요일이니깐..)
        System.out.println("temp1 : " + today.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)));         // 2018-03-06. 다음의 화요일 구하기
        System.out.println("temp1 : " + today.with(TemporalAdjusters.previous(DayOfWeek.MONDAY)));      // 2018-02-26. 이전의 월요일 구하기
        System.out.println("temp1 : " + today.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)));      // 2018-03-04. 이전의 일요일 구하기 (3월 5일이 월요일이니깐..)
        System.out.println("temp2 : " + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));          // 2018-03-05. 다음의 월요일 구하기. 이건 오늘 날짜로 구해버린다.
        System.out.println("temp2 : " + today.with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)));         // 2018-03-06. 다음의 화요일 구하기
        System.out.println("temp2 : " + today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));      // 2018-03-05. 이전의 월요일 구하기. 오늘을 포함하냐 않하냐 차이
        System.out.println("temp2 : " + today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));      // 2018-03-04. 이전의 일요일 구하기

        System.out.println("temp3 : " + today.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.MONDAY)));   // 2018-03-12. 해당 월의 2번째 월요일
        System.out.println("temp3 : " + today.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY)));   // 2018-03-09. 해당 월의 2번째 금요일
        System.out.println("temp3 : " + today.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));   // 2018-03-30. 해당 월의 마지막 금요일
        System.out.println("temp4 : " + today.with(TemporalAdjusters.firstDayOfMonth()));       // 2018-03-01. 이 달의 첫번째 날
        System.out.println("temp4 : " + today.with(TemporalAdjusters.firstDayOfNextMonth()));   // 2018-04-01. 다음 달의 첫번째 날
        System.out.println("temp4 : " + today.with(TemporalAdjusters.firstDayOfNextYear()));    // 2019-01-01. 내년의 첫번째 날
        System.out.println("temp4 : " + today.with(TemporalAdjusters.lastDayOfMonth()));        // 2018-03-31. 이 달의 마지막 날
        System.out.println("temp4 : " + today.with(TemporalAdjusters.lastDayOfYear()));         // 2018-12-31. 올해의 마지막 날


//        Instant start = Instant.now();
//        Instant start2 = Clock.systemUTC().instant();
//
//        test3();
//
//        Instant end = Instant.now();
//        Duration timeElapsed = Duration.between(start, end);
//        long millis = timeElapsed.toMillis();
//        System.out.println("millis : " + millis);
    }

    public static void test2() {
        LocalTime now = LocalTime.of(15, 10, 20);
        System.out.println("now : " + now);         // 15:10:20

//        LocalDate today = LocalDate.now();
//        System.out.println("today : " + today);
//        LocalDate alonezosBirthday = LocalDate.of(1903, 6, 14);
//        alonezosBirthday = LocalDate.of(1903, Month.JUNE, 14);
//
//        LocalDate programmerDay = LocalDate.of(2018, 1, 1).plusDays(256);
//        System.out.println("programmerDay : " + programmerDay);

    }

    public static void test3() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
