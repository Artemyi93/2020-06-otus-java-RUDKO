package ru.otus.storage;

import java.util.List;


public interface Storage {

    // добавить запись
    void add(History record);

    // вернуть все записи
    List<History> getAll();
}
