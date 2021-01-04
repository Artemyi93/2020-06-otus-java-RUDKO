package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class BirthDayTest {

    private int year;
    private int month;
    private int day;
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
    public void checkToStringMethod() throws Exception {
        String expectedString = String.format("%04d-%02d-%02d", year, month, day);
        assertTrue(birthDay.toString().equals(expectedString));
    }

    @Test
    public void checkThanAgePositive() throws Exception {
        assertTrue(birthDay.getAge() >= 0);
    }

    @After
    public void afterTest() {
    }

    private void assertTrue(boolean expression) throws Exception {
        if (!expression) {
            throw new Exception("Ошибка! Тест не пройден.");
        }
    }
}
