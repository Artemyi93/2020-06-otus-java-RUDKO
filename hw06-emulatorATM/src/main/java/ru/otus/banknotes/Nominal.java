package ru.otus.banknotes;

public enum Nominal {

    B1000(1000), B500(500), B200(200), B100(100);
    private int nominal;

    Nominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
