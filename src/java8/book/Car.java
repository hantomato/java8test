package java8.book;

/**
 * Created by neowiztomato on 2018-01-29.
 */
public interface Car {
    public int getAccount();
    default public String getCarName() {
        return "car";
    }
}
