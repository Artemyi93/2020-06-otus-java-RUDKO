package ru.otus.storage;

import ru.otus.banknotes.Banknote;
import ru.otus.banknotes.Nominal;

// хранилище денег в банкомате
public interface Storage {

    // добавить купюру
    void add(Banknote banknote);

    // остаток купюр по указанному номиналу
    int countOfBanknotesByNominal(Nominal nominal);

    // вернуть указанное количество банкнот определенного типа
    void getBanknotesByNominal(Nominal nominal, int count);

    // получить остаток средств
    int getBalance();

}
