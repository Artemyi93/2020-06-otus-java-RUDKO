package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class BirthDayTest {

    private int year;
    private int month;
    private int day;
    private boolean result = false;
    private BirthDay birthDay;

    public BirthDayTest(int[] numbs) {
        year = numbs[0];
        month = numbs[1];
        day = numbs[2];
    }

    @Before
    public void globalSetUp() {
        birthDay = new BirthDay(year, month, day);
    }

    @Test
    public void checkToStringMethod() {
        String date = String.format("%04d-%02d-%02d", year, month, day);
        result = birthDay.toString().equals(date);
    }

    @Test
    public void checkThanAgePositive() {
        result = birthDay.getAge() >= 0;
    }

    @After
    public void afterTest() {
    }

    public boolean getResult() {
        return result;
    }
}
