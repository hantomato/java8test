package java8.book;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
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
//        test2();
//        test3();
//        exercise1();
//        exercise2();
//        exercise3();
        exercise4();
        exercise5();
        exercise10();
    }

    public static void test0() {
        LocalDate today = LocalDate.of(2018, 3, 5);
        System.out.println("test LocalDate");
        System.out.println("today : " + today);                     // 2018-03-05
        System.out.println("temp1 : " + today.plusDays(3));         // 2018-03-08. 3일 더한 날
        System.out.println("temp1 : " + today.plusDays(30));        // 2018-04-04. 30일 더한 날
        System.out.println("temp1 : " + today.minusDays(3));        // 2018-03-02. 3일 뺀 날
        System.out.println("temp1 : " + today.minusDays(10));       // 2018-02-23. 10일 뺀 날
        System.out.println("temp2 : " + today.plusWeeks(1));        // 2018-03-12. 7일 더한 날
        System.out.println("temp2 : " + today.plusWeeks(6));        // 2018-04-16. 42일 더한 날
        System.out.println("temp2 : " + today.plusDays(6*7));       // 2018-04-16. 상동
        System.out.println("temp2 : " + today.minusWeeks(1));       // 2018-02-26. 7일 뺀 날
        System.out.println("temp2 : " + today.minusWeeks(6));       // 2018-01-22. 42일 뺀 날
        System.out.println("temp2 : " + today.minusDays(6*7));      // 2018-01-22. 상동

        System.out.println("temp3 : " + today.withDayOfMonth(1));    // 2018-03-01. 그 달의 1번째 날
        System.out.println("temp3 : " + today.withDayOfMonth(25));   // 2018-03-25. 그 달의 25번째 날
        System.out.println("temp3 : " + today.withDayOfYear(5));     // 2018-01-05. 그 해의 5번째 날
        System.out.println("temp3 : " + today.withDayOfYear(40));    // 2018-02-09. 그 해의 40번째 날
        System.out.println("temp3 : " + today.withMonth(1));         // 2018-01-05. 달을 1로 바꾼다.
        System.out.println("temp3 : " + today.withMonth(5));         // 2018-05-05. 달을 5로 바꾼다.
        //System.out.println("temp3 : " + today.withMonth(15));       // java.time.DateTimeException: Invalid value for MonthOfYear (valid values 1 - 12): 15
        System.out.println("temp3 : " + today.withYear(1));          // 0001-03-05. 년도를 1로 바꾼다.
        System.out.println("temp3 : " + today.withYear(100));        // 0100-03-05. 년도를 100으로 바꾼다.
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
        System.out.println("temp4 : " + now.plusHours(25));     // 16:10:20
        System.out.println("temp4 : " + now.plusHours(50));     // 17:10:20
        System.out.println("temp4 : " + now.plusMinutes(20));   // 15:30:20
        System.out.println("temp4 : " + now.plusMinutes(80));   // 16:30:20
        System.out.println("temp4 : " + now.plusSeconds(25));   // 15:10:45
        System.out.println("temp4 : " + now.plusNanos(2500));   // 15:10:20.000002500

        // LocalDate, LocalTime, LocalDateTime
        LocalDateTime ldt = LocalDateTime.of(2018,3, 5, 15, 10, 20);
        System.out.println("temp5 : " + ldt);

//        LocalDate today = LocalDate.now();
//        System.out.println("today : " + today);
//        LocalDate alonezosBirthday = LocalDate.of(1903, 6, 14);
//        alonezosBirthday = LocalDate.of(1903, Month.JUNE, 14);
//
//        LocalDate programmerDay = LocalDate.of(2018, 1, 1).plusDays(256);
//        System.out.println("programmerDay : " + programmerDay);

    }

    public static void test3() {
        ZoneId.getAvailableZoneIds();
        ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
                // 1969-07-16T09:32-04:00[America/New_York]
        // UTC : 세계 협정시 Coordinated Universal Time)



        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void exercise1() {
        System.out.println("===== exercise 1 =====");
        // PlusDays를 사용하지 않고 프로그래머의 날을 계산하다.
        // 프로그래머의 날은 년 시작후 256일째의 날
        LocalDate ld = LocalDate.of(2018, 1, 1);
        System.out.println("programmer day : " + ld.plusDays(255));
        System.out.println("programmer day : " + ld.withDayOfYear(256));

    }

    public static void exercise2() {
        System.out.println("===== exercise 2 =====");
        // LocalDate.of(2000, 2, 29)에 1년을 더하면 어떻게 되는가? 한편 4년을 더할 때는 어떠한가?
        // 마지막으로 1년을 4번 더하면 어떻게 되는가?
        // 윤년 : 2000, 2004, 2008, 2012, 2016 ...
        LocalDate ld = LocalDate.of(2000, 2, 29);
        System.out.println("" + ld.plusYears(1));   // 2001-02-28
        System.out.println("" + ld.plusYears(4));   // 2004-02-29
        LocalDate ldTemp;
        ldTemp = ld.plusYears(1);
        System.out.println("ldTemp : " + ldTemp);   // 2001-02-28
        ldTemp = ldTemp.plusYears(1);
        System.out.println("ldTemp : " + ldTemp);   // 2002-02-28
        ldTemp = ldTemp.plusYears(1);
        System.out.println("ldTemp : " + ldTemp);   // 2003-02-28
        ldTemp = ldTemp.plusYears(1);
        System.out.println("ldTemp : " + ldTemp);   // 2004-02-28   // 1년씩 4번 더하면 2/29일이 아니고 2/28일이다.
    }

    public static void exercise3() {
        System.out.println("===== exercise 3 =====");
    }

    public static void exercise4() {
        System.out.println("===== exercise 4 =====");
        // 지정한 월의 달력을 표시하는 유닌ㄱ스 cal 프로그램에 해당하는 프로그램을 작성하라.
        // 예를 들어, java Cal 3 2013 을 실행하면 다음과 같이 표시하고, 3월 1일이 금요일임을 나타내야한다.
        // 주말을 주의 끝 부분에 표시한다.
        printMonth(2013, 3);
//                     1  2  3
//         4  5  6  7  8  9 10
//        11 12 13 14 15 16 17
//        18 19 20 21 22 23 24
//        25 26 27 28 29 30 31
    }

    public static void printMonth(int year, int month) {
        LocalDate ld = LocalDate.of(year, month, 1);
        //System.out.println("ldTemp : " + ld.getDayOfWeek());

        // 첫째줄 출력
        for (int i=1; i<ld.getDayOfWeek().getValue(); ++i) {
            System.out.print("   ");
        }

        // 다음줄 출력
        while (ld.getMonthValue() == month) {
            if (ld.getDayOfWeek() == DayOfWeek.MONDAY) {
                System.out.println("");
            }
            System.out.print(" " + String.format("%2d", ld.getDayOfMonth()));
            ld = ld.plusDays(1);
        }
        System.out.println("");


    }

    public static void exercise5() {
        System.out.println("===== exercise 5 =====");
        // 지금까지 며칠을 살아왔는지 출력하는 프로그램을 작성하라.
        System.out.println("days : " + calculateMyDays(2018, 3, 5));
        System.out.println("days : " + calculateMyDays(1977, 3, 22));
    }

    public static long calculateMyDays(int year, int month, int day) {
        LocalDate begin = LocalDate.of(year, month, day);
        long beginEpochDay = begin.toEpochDay();
        long currentEpochDay = Instant.now().toEpochMilli()/1000/60/60/24;
        System.out.println("beginEpochDay : " + beginEpochDay);
        System.out.println("currentEpochDay : " + currentEpochDay);
        return currentEpochDay - beginEpochDay;
    }

    public static void exercise10() {
        System.out.println("===== exercise 10 =====");
        // 로스엔젤레스에서 프랑크푸르트로 가는 항공기가 지역 시간으로 오후 3:05에 출발하고, 10시간 50분이 걸린다.
        // 항공기는 언제 도착하겠는가? 이와 같은 계산을 처리할 수 있는 프로그램을 작성하라.

        // 로스엔젤레스 : UTC-8.  America/Los_Angeles
        // 프랑크푸르트 : UTC+1   Europe/Berlin
        // Asia/Seoul
        String arrivalTime = calculate(ZoneId.of("America/Los_Angeles"), ZoneId.of("Europe/Berlin"), "03:05", "10:50");
        System.out.println("arrivalTime : " + arrivalTime);
    }

    public static String calculate(ZoneId from, ZoneId to, String startTime, String durationTime) {

        // 1. 도착시간 계산
        LocalTime startLt = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));       // 출발 시간
        LocalTime durationLt = LocalTime.parse(durationTime, DateTimeFormatter.ofPattern("HH:mm")); // 걸린 시간
        LocalTime endLt = startLt.plusSeconds(durationLt.toSecondOfDay());                          // 도착 시간
        System.out.println("startLt : " + startLt);
        System.out.println("durationLt : " + durationLt);
        System.out.println("endLt : " + endLt);

        // 2. 도착시간을 ZonedDateTime 로 구하기
        ZonedDateTime now = ZonedDateTime.now(from);
        System.out.println("now : " + now);
        ZonedDateTime now2 = ZonedDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                endLt.getHour(), endLt.getMinute(), endLt.getSecond(), 0, from);
        System.out.println("now2 : " + now2);   // now2는 오늘 날짜의 도착시간을 의미

        // 3. zoneId 변경
        ZonedDateTime now3 = now2.toInstant().atZone(ZoneId.of("Asia/Jakarta"));    // Zone 없는 시간(Instant) 구해서 Zone 을 변경함.
        System.out.println("now3 : " + now3);   // now2는 오늘 날짜의 도착시간을 의미

//        now : 2018-03-06T06:12:38.831-08:00[America/Los_Angeles]
//        now2 : 2018-03-06T13:55-08:00[America/Los_Angeles]    // 오늘 날짜 기준으로 출발지 기준의 도착 시간
//        now3 : 2018-03-07T04:55+07:00[Asia/Jakarta]           // 도착지 기준의 도착 시간
        return now3.toString();
    }


}
