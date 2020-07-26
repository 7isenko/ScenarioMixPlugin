package io.github._7isenko.scenariomix.utils;

public class Calculator {
    /**
     * @return best value ( a multiple of 9 )
     */
    public static int calculateInventorySize(int currentSize) {
        return 9 * ((int) Math.ceil(currentSize / 9.0));
    }
}
