package com.duyi.horizontalscrollviewdemo.three.view;

import java.util.LinkedList;
import java.util.List;

public class FLStack<T> {
    private List<T> list = new LinkedList<>();

    public void push(T t) {
        list.add(t);
    }

    public T pop() {
        if (!list.isEmpty()) {
            return list.remove(list.size() - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
