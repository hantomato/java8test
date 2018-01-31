package java8.book;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 주요 사항
 * java8 interface 변경사항
 *      http://blog.powerumc.kr/473
 * default method
 * method reference :: 중요
 *
 * Created by neowiztomato on 2018-01-22.
 */
public class NmjChapter1 {

    public String name1 = "nmj";
    public static String name2 = "nmj";
    public static void main(String... argc) {
        System.out.println("===== main start ==========");
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
        exercise7();
        exercise8();
        exercise9();
    }

    public void doWork() {
        Runnable runner = () -> {
            this.doWork();
            System.out.println("    this : " + this.name1);

        };
    }

    public static void exercise1() {
        // 01. Arrays.sort 메서드에서 비교자 코드는 sort 호출과 같은 스레드에서 호출되는가, 다른 스레드에서 호출되는가?
        // 정답 : 같은 쓰레드에서 호출된다.
        System.out.println("===== exercise 1 =====");

        System.out.println("First test - main Thread");
        _exercise1();

//        System.out.println("Second test - worker Thread");
//        Thread thd = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                _exercise1();
//            }
//        });
//        thd.start();

    }

    public static void _exercise1() {
        System.out.println("  Thread : " + Thread.currentThread().getName());
        Integer[] aaa = new Integer[] {6,8,3};
        Arrays.sort(aaa, (x, y) -> {
            System.out.println("    Lambda Thread : " + Thread.currentThread().getName());
            return Integer.compare(x,y);
        });
    }

    public static boolean checkDirectory(File file) {
        return file.isDirectory();
    }

    public static void exercise2() {
        // 02. java.io.File 클래스의 listFiles(FileFilter)와 isDirectory 메서드를 이용해 주어진 디렉터리의
        // 모든 서브디렉터리를 리턴하는 메서드를 작성하라. FileFilter 객체 대신 람다 표현식을 사용하라.
        // 메서드 표현식을 이용해 같은 작업을 반복하라.
        System.out.println("===== exercise 2 =====");
        File file = new File("d:\\work\\bbm");
        File[] files1 = file.listFiles(x -> x.isDirectory());
        File[] files2 = file.listFiles(NmjChapter1::checkDirectory);
        for (File item : files1) {
            System.out.println(item.getName());
        }
        for (File item : files2) {
            System.out.println(item.getName());
        }

        System.out.println("");
    }

    public static void exercise3() {
        // 03. java.io.File 클래스의 list(FilenameFilter) 메서드를 이용해 주어진 디렉터리에서 주어진 확장자를
        // 지닌 모든 파일을 리턴하는 메서드를 작성하라. FilenameFilter가 아닌 람다 표현식을 사용하라.
        // 이 람다 표현식이 자신이 감싸는 유효 범위에 있는 어느 변수를 캡처하는가?
        // => 메서드의 파라미터를 모두 캡처한다.
        System.out.println("===== exercise 3 =====");

        String [] ret = _exercise3(new File("d:\\file\\gg"), "pptx");
        for (String item : ret) {
            System.out.println("item : " + item);
        }
    }

    public static String[] _exercise3(File directory, String ext) {
        String[] ret = directory.list((dir, name) -> {
//            ext = ""; // error
            if (ext.equals(getFileExtension(name))) {
                return true;
            }
            return false;
        });
        return ret;
    }

    public static String getFileExtension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public static void exercise4() {
        // 04. File 객체 배열이 주어졌을 때, 디렉터리가 파일보다 앞에 위치하고 각 그룹 안에서 요소들이 경로 이름에 따라
        // 정렬되도록 정렬하라. Comparator가 아닌 람다 표현식을 사용하라.
        System.out.println("===== exercise 4 =====");
        File[] files = new File("d:\\work\\bbm").listFiles();
        for (File item : files) {
            System.out.println(item.toString());
        }
        System.out.println("정렬 후");
        Arrays.sort(files, (x, y) -> x.isDirectory() ? -1 : 1);
        for (File item : files) {
            System.out.println(item.toString());
        }
    }

    public static void exercise5() {
        // 05. 다수의 ActionListener, Runnable 등을 포함하는 프로젝트 중 하나에서 파일을 불러와서 이러한 인터페이스를
        // 람다 표현식으로 교체하라. 이 교체 작업으로 몇 행을 줄였는가? 코드가 더 읽기 쉬워졌는가?
        // 메서드 레퍼런스를 사용할 수 있었는가?
        // => yes
        System.out.println("===== exercise 5 =====");
    }

    public static void exercise6() {
        // 06. Runnable에서 검사 예외를 다뤄야 하는 점이 싫지 않은가?
        // 모든 검사 예외를 잡아내서 비검사 예외로 바꾸는 uncheck 메서드를 작성하라. 예를 들면, 다음과 같이 사용할 것이다.
        //      new Thread(uncheck(
        //          () -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
        //          // 여길 보자. catch (InterruptedException) 부분이 없다!
        // 힌트: run 메서드에서 모든 예외를 던질 수 잇는 RunnableEx라는 인터페이스를 정의한다.
        // 다음으로 public static Runnable uncheck(RunnableEx runner)를 구현한다. uncheck 함수 안에서 람다 표현식을 사용한다.
        // RunnableEx 대신 단순히 Callable<Void>를 사용할 수 없는 이유는 무엇인가?
        System.out.println("===== exercise 6 =====");
    }

    public static void exercise7() {
        // Runnable 인스턴스 두 개를 파라미터로 받고, 첫 번째 인스턴스를 실행한 후 두 번째를 실행하는 Runnable을
        // 리턴하는 andThen 이라는 정적 메서드를 작성하라. main 메서드에서 andThen 호출에 람다 표현식 두 개를 전달하고
        // 결과로 받은 인스턴스를 실행하라.
        System.out.println("===== exercise 7 =====");
        andThen(() -> System.out.println("execute x"), () -> System.out.println("execute y")).run();
    }

    public static Runnable andThen(Runnable x, Runnable y) {
        return new Runnable() {
            @Override
            public void run() {
                x.run();
                y.run();
            }
        };
    }

    public static void exercise8() {
        // 람다 표현식이 다음과 같은 향상된 for 루프에 있는 변수를 캡처할 때 무슨 일이 일어나는가?
        // 규칙에 맞는 작업인가? 각 람다 표현식이 다른 값을 캠처하는가, 아니면 모두 마지막 값을 얻느가?
        // 만일 전통적인 루프인 for (int i=0; i<names.length; i++) 를 사용하면 무슨 일이 일어나는가?
        // => final 이 아닌 변수는 람다표현식 내에서 사용이 불가능하다.
        // => error message : Variable used in lambda expression should be final or effectively final
        System.out.println("===== exercise 8 =====");

        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));

        for (int i=0; i<names.length; i++) {
            runners.add(() -> {
//                System.out.println(names[i]);
            });
        }
    }

    public static void exercise9() {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.forEach(System.out::println);
        list.forEach(x -> System.out.println(x));
    }

}
