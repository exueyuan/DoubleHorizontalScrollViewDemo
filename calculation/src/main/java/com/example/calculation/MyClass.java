package com.example.calculation;

public class MyClass {
    public static void main(String[] args) {
        System.out.println(getNum2(25));
    }

    public static int getNum(int x) {
        int a = x % 2000;
        if (a < 0){
            a += 2000;
        }
        if (a <= 30) {
            return a;
        } else {
            return a - 2000;
        }
    }

    public static int getNum2(int x) {
        int allLength = 10 + 3;
        int a = x % allLength;
        if (a < 0) {
            a += allLength;
        }
        if (a <= 10) {
            return a;
        } else {
            return a - allLength;
        }
    }
}
