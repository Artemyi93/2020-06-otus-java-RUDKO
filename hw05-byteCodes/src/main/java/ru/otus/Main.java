package ru.otus;

public class Main {
    public static void main(String... args) throws Exception {
        Calculator myCalculator = Ioc.createMyClass();
        myCalculator.calculation(2);
        myCalculator.calculation(3, 5);
        myCalculator.calculation(6, 8, "add");
    }
}
