package ru.otus;

public class CalculatorImpl implements Calculator {

    @Log
    @Override
    public void calculation(int param1) {
        System.out.println(String.format("%d^2 = %d", param1, param1 * param1));
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println(String.format("%d * %d = %d", param1, param2, param1 * param2));
    }

    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        switch (param3) {
            case "add":
                System.out.println(String.format("%d + %d = %d", param1, param2, param1 + param2));
                break;
            case "diff":
                System.out.println(String.format("%d - %d = %d", param1, param2, param1 - param2));
                break;
            case "multiplication":
                System.out.println(String.format("%d * %d = %d", param1, param2, param1 * param2));
                break;
            default:
                System.out.println("Wrong operation!");
                break;
        }
    }
}
