package com.mg105;

public class HelloWorld {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(convert(i));
        }
    }

    public static String convert(int decide) {
        if (decide % 15 == 0) {
            return "com.mg105.HelloWorld";
        }
        if (decide % 3 == 0) {
            return "Hello";
        }
        if (decide % 5 == 0) {
            return "World";
        }
        return String.valueOf(decide);
    }
}
