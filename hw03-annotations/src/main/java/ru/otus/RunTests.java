package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RunTests {

    static int count = 0;    // всего
    static int success = 0;  // успешно
    static int fail = 0;     // с ошибкой

    static void runTestsBirthDay(Class clazz, Collection testData) throws Exception {

        // конструктор класса BirthDayTest и массив его публичных методов
        Constructor<BirthDayTest> constructor = clazz.getDeclaredConstructor(int[].class);
        Method[] methods = clazz.getMethods();

        // списки методов для тестирования
        List<Method> methodsTest = new ArrayList<>();
        List<Method> methodsBefore = new ArrayList<>();
        List<Method> methodsAfter = new ArrayList<>();

        for (Method met : methods) {
            if (met.isAnnotationPresent(Test.class)) {
                methodsTest.add(met);
            } else if (met.isAnnotationPresent(Before.class)) {
                methodsBefore.add(met);
            } else if (met.isAnnotationPresent(After.class)) {
                methodsAfter.add(met);
            }
        }

        // проводим тестирование на объектах тест-класса BirthDayTest
        for (Object objectTestData : testData) {

            // создаем объект класса-теста
            BirthDayTest object = constructor.newInstance(objectTestData);

            for (Method metTest : methodsTest) {
                // методы @Before
                for (Method metBefore : methodsBefore) {
                    metBefore.invoke(object);
                }
                // текущий метод @Test
                metTest.invoke(object);
                count++;
                if (object.getResult()) {
                    success++;
                } else {
                    fail++;
                }
                // методы @After
                for (Method metAfter : methodsAfter) {
                    metAfter.invoke(object);
                }
            }
        }
        // результаты
        System.out.println("All: " + count + " Success: " + success + " Failed: " + fail);
    }
}
