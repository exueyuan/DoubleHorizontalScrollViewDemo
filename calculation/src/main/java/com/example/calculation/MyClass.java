package com.example.calculation;

public class MyClass {
    public static void main(String[] args) {
        System.out.println(getNum(-1));
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
}
