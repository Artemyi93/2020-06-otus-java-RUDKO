package ru.otus.cassette;

import ru.otus.banknotes.Banknote;

import java.util.ArrayList;
import java.util.List;

public class CassetteBanknotes<T extends Banknote> implements Cassette<T> {

    private List<T> banknotes = new ArrayList<>();

    @Override
    public void addBanknote(T banknote) {
        banknotes.add(banknote);
    }

    @Override
    public int countOfBanknotes() {
        return banknotes.size();
    }

}
