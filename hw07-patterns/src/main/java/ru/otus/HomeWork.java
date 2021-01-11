package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinter;
import ru.otus.listener.homework.ListenerHistory;
import ru.otus.processor.homework.LoggerProcessorException;
import ru.otus.processor.homework.ProcessorReplaceField11Field12;
import ru.otus.storage.StorageImpl;

import java.util.Arrays;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */

        var processors = List.of(new ProcessorReplaceField11Field12(),
                new LoggerProcessorException(message -> message));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {
        });

        var listenerPrinter = new ListenerPrinter();
        var storage = new StorageImpl();
        var listenerHistory = new ListenerHistory(storage);
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(listenerHistory);

        List<String> coolStringList = Arrays.asList("Java", "Scala", "Groovy");
        ObjectForMessage objectForMessage = new ObjectForMessage(coolStringList);

        var message = new Message.Builder()
                .field11("field11")
                .field12("field12")
                .field13(objectForMessage)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);
        System.out.println(storage.getAll().get(0).getOldMsg());
        System.out.println(storage.getAll().get(0).getNewMsg());

        complexProcessor.removeListener(listenerPrinter);
        complexProcessor.removeListener(listenerHistory);
    }
}
