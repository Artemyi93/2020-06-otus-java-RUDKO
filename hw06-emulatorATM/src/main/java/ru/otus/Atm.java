package ru.otus;

import ru.otus.banknotes.Banknote;

// банкомат
public interface Atm {

    // положить деньги
    void add(Banknote... banknotes);

    // снять деньги
    boolean getMoney(int amount);

    // узнать баланс
    int getBalance();
}
