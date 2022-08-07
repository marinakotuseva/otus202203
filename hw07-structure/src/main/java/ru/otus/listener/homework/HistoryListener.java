package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.SerializationUtils;

public class HistoryListener implements Listener, HistoryReader {

    private final Set<Message> history = new HashSet<>();

    @Override
    public void onUpdated(Message msg) {
        history.add(SerializationUtils.clone(msg));
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return history.stream().filter(state -> state.getId() == id).findFirst();
    }
}
