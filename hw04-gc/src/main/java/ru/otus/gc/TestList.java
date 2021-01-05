package ru.otus.gc;

import java.util.ArrayList;
import java.util.List;

// список для заполнения памяти
public class TestList {

    // без подтекания памяти
    private List<Double> list = new ArrayList<>();
    // с подтеканием памяти (из-за статических полей)
//    private static List<Double> list = new ArrayList<>();
    private int size;

    public TestList(int size) {
        this.size = size;
    }

    public void addElements() {
        for (int i = 0; i < size; i++) {
            list.add(Math.random());
        }
    }
}