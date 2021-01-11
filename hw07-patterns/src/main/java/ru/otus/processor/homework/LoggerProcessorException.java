package ru.otus.processor.homework;

import ru.otus.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class LoggerProcessorException implements Processor {

    private final Processor processor;

    public LoggerProcessorException(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {

        int second = LocalDateTime.now().getSecond();
        if (second % 2 == 0) {
            throw new RuntimeException("Ошибка! Четная секунда (" + second + ").");
        }

        return processor.process(message);
    }
}
