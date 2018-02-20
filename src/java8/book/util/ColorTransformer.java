package java8.book.util;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}
