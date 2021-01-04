package ru.otus;

import java.time.LocalDate;
import java.time.Period;

public class BirthDay {
    private LocalDate birthDay;

    public BirthDay(int year, int month, int day) {
        this.birthDay = LocalDate.of(year, month, day);
    }

    public int getAge() {
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return birthDay.toString();
    }
}
