package ru.otus.storage;

import ru.otus.banknotes.*;
import ru.otus.cassette.*;

public class CassetteStorage implements Storage {

    private Cassette100 cassette100 = new Cassette100();
    private Cassette200 cassette200 = new Cassette200();
    private Cassette500 cassette500 = new Cassette500();
    private Cassette1000 cassette1000 = new Cassette1000();
    private int balance;

    public void add(Banknote banknote) {
        if (banknote instanceof Banknote100) {
            addBanknoteInCassette(cassette100, (Banknote100) banknote);
        } else if (banknote instanceof Banknote200) {
            addBanknoteInCassette(cassette200, (Banknote200) banknote);
        } else if (banknote instanceof Banknote500) {
            addBanknoteInCassette(cassette500, (Banknote500) banknote);
        } else if (banknote instanceof Banknote1000) {
            addBanknoteInCassette(cassette1000, (Banknote1000) banknote);
        }
    }

    // добавить банкноту в соответствующую номиналу кассету
    private <T extends Banknote> void addBanknoteInCassette(Cassette<T> cassette, T banknote) {
        cassette.addBanknote(banknote);
        incBalance(banknote);
    }

    // увеличить баланс на номинал банкноты
    private <T extends Banknote> void incBalance(T banknote) {
        balance += banknote.getNominal();
    }

    public int countOfBanknotesByNominal(Nominal nominal) {
        return getCassetteByNominal(nominal).countOfBanknotes();
    }

    // определяет кассету для указанного номинала
    private Cassette<? extends Banknote> getCassetteByNominal(Nominal nominal) {
        switch (nominal) {
            case B100:
                return cassette100;
            case B200:
                return cassette200;
            case B500:
                return cassette500;
            case B1000:
                return cassette1000;
            default:
                throw new UnsupportedOperationException("Неизвестный номинал!");
        }
    }

    @Override
    public void getBanknotesByNominal(Nominal nominal, int count) {
        if (countOfBanknotesByNominal(nominal) < count) {
            return;
        }
        decBalance(nominal, count);
    }

    // уменьшить баланс на count банкнот заданного номинала
    private void decBalance(Nominal nominal, int count) {
        balance -= nominal.getNominal() * count;
    }

    public int getBalance() {
        return balance;
    }

}
