package ru.otus.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StorageImpl implements Storage {

    private final List<History> storage = new ArrayList<>();

    @Override
    public void add(History record) {
        storage.add(record);
    }

    @Override
    public List<History> getAll() {
        return Collections.unmodifiableList(storage);
    }
}
