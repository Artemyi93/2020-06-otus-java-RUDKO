package ru.otus.cassette;

import ru.otus.banknotes.Banknote;

// кассета с определенным номиналом
public interface Cassette<T extends Banknote> {

    // добавить банкноту в кассету
    void addBanknote(T banknote);

    // число купюр в кассете
    int countOfBanknotes();
}
