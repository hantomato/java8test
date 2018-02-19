package java8.book.util;

/**
 * Created by neowiztomato on 2018-01-23.
 */
@FunctionalInterface
public interface MyInterface<T> {
    public void onSucceed(T msg);
    default boolean didItWork(int i, double x, String s) {
        if (i > 0)
            return true;
        return false;
    }
}
