package ru.otus.banknotes;

public abstract class Banknote {

    private final Nominal nominal;

    Banknote(Nominal nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal.getNominal();
    }
}
