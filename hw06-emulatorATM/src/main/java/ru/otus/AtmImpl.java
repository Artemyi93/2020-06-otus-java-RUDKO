package ru.otus;

import ru.otus.banknotes.Banknote;
import ru.otus.banknotes.Nominal;
import ru.otus.storage.CassetteStorage;
import ru.otus.storage.Storage;

import java.util.EnumMap;
import java.util.Map;

public class AtmImpl implements Atm {

    // хранилище денег
    private Storage storage = new CassetteStorage();
    // минимальная сумма для выдачи
    static final int MIN_AMOUNT = Nominal.B100.getNominal();

    @Override
    public void add(Banknote... banknotes) {
        for (Banknote banknote : banknotes) {
            storage.add(banknote);
        }
    }

    @Override
    public boolean getMoney(int amount) {
        if (storage.getBalance() < amount) {
            System.out.println("Операция не может быть выполнена. Недостаточно денег в банкомате.");
            return false;
        }
        if (amount < MIN_AMOUNT) {
            System.out.println("Операция не может быть выполнена. Сумма меньше минимального номинала.");
            return false;
        }
        try {
            getBanknotes(amount);
        } catch (Exception ex) {
            System.out.println("Операция не может быть выполнена. Невозможно собрать сумму из купюр в наличии.");
            return false;
        }

        return true;
    }

    // операция выдачи денег
    private void getBanknotes(int amount) throws Exception {
        Map<Nominal, Integer> minCountOfBanknotes = getMapWithCountsOfBanknotesToAmount(amount);
        // проверяем, можно ли выдать запрошенную сумму
        if (isOperationConfirmed(minCountOfBanknotes, amount)) {
            getBanknotesFromStorage(minCountOfBanknotes);
        } else {
            throw new Exception("Невозможно собрать сумму из имеющихся банкнот");
        }
    }

    // возвращает Map, у которой key - номинал купюры, value - количество купюр
    private Map<Nominal, Integer> getMapWithCountsOfBanknotesToAmount(int amount) {
        int tempAmount = amount;
        Map<Nominal, Integer> mapCountOfBanknotes = new EnumMap<>(Nominal.class);
        for (Nominal nominal : Nominal.values()) {
            int division = tempAmount / nominal.getNominal();
            if (division >= 1) {
                int banknoteAvailability = storage.countOfBanknotesByNominal(nominal) - division;
                int returnCount = division;
                if (banknoteAvailability < 0) {
                    returnCount = division + banknoteAvailability;
                }
                tempAmount -= nominal.getNominal() * returnCount;
                mapCountOfBanknotes.put(nominal, returnCount);
            }
        }
        return mapCountOfBanknotes;
    }

    // проверка, набирается ли указанная сумма из имеющихся купюр
    private boolean isOperationConfirmed(Map<Nominal, Integer> desiredBanknotes, int amount) {
        int sum = desiredBanknotes.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getNominal() * entry.getValue())
                .reduce(0, Integer::sum);
        return sum == amount;
    }

    // выдать купюры из хранилища
    private void getBanknotesFromStorage(Map<Nominal, Integer> desiredBanknotes) {
        for (Map.Entry<Nominal, Integer> entry : desiredBanknotes.entrySet()) {
            storage.getBanknotesByNominal(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public int getBalance() {
        return storage.getBalance();
    }
}
