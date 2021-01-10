package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.banknotes.Banknote100;
import ru.otus.banknotes.Banknote1000;
import ru.otus.banknotes.Banknote200;
import ru.otus.banknotes.Banknote500;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тестирование банкомата")
class AtmImplTest {

    private Atm atm;

    @BeforeEach
    void setAtm() {
        atm = new AtmImpl();
    }

    @DisplayName("Проверяем добавление купюр и запрос остатка средств")
    @Test
    void checkMoneyAddAndBalance() {

        // добавим одну купюру
        atm.add(new Banknote100());
        assertThat(atm.getBalance()).isEqualTo(100);

        // добавим несколько купюр по одной
        atm.add(new Banknote200());
        atm.add(new Banknote500());
        atm.add(new Banknote1000());
        assertThat(atm.getBalance()).isEqualTo(1800);

        // добавим сразу 100 и 500
        atm.add(new Banknote100(), new Banknote500());
        assertThat(atm.getBalance()).isEqualTo(2400);
    }

    @DisplayName("Проверяем запрос суммы, меньшей минимального номинала")
    @Test
    void checkGetLessThanMinimalBanknote() {
        atm.add(new Banknote100());
        assertFalse(atm.getMoney(10));
    }

    @DisplayName("Проверяем запрос суммы, большей чем остаток средств")
    @Test
    void checkGetMoreThanBalance() {
        atm.add(new Banknote100());
        assertFalse(atm.getMoney(200));
    }

    @DisplayName("Проверяем запрос суммы, которую банкомат может выдать, и остаток средств после выдачи")
    @Test
    void checkGetPositive() {
        // добавим 300
        atm.add(new Banknote100(), new Banknote200());
        // снимем 200 и проверим, что осталось 100
        assertTrue(atm.getMoney(200));
        assertThat(atm.getBalance()).isEqualTo(100);
    }

    @DisplayName("Проверяем запрос суммы, которую банкомат не может выдать из-за отсутствия необходимых купюр")
    @Test
    void checkGetImpossibleToCollect() {
        // добавим 700
        atm.add(new Banknote200(), new Banknote500());
        // проверим, что нельзя снять 300
        assertFalse(atm.getMoney(300));
        // проверим, что в банкомате остались 700
        assertThat(atm.getBalance()).isEqualTo(700);
    }
}