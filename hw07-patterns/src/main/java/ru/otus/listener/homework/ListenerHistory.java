package ru.otus.listener.homework;


import ru.otus.Message;
import ru.otus.listener.Listener;
import ru.otus.storage.History;
import ru.otus.storage.Storage;

public class ListenerHistory implements Listener {

    private final Storage storage;

    public ListenerHistory(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        History history = new History(oldMsg, newMsg);
        storage.add(history);
    }
}
