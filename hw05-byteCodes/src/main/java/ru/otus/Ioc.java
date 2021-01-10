package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ioc {
    // список методов с аннотацией @Log
    private static List<Method> methodsLog = new ArrayList<>();

    private Ioc() {
    }

    static Calculator createMyClass() throws ClassNotFoundException {
        // получаем класс CalculatorImpl и его публ. методы
        Class<?> clazz = Class.forName("ru.otus.CalculatorImpl");
        Method[] methods = clazz.getMethods();

        // заполняем список methodsLog
        for (Method met : methods) {
            if (met.isAnnotationPresent(Log.class)) {
                methodsLog.add(met);
            }
        }

        InvocationHandler handler = new DemoInvocationHandler(new CalculatorImpl());
        return (Calculator) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{Calculator.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Calculator myClass;

        DemoInvocationHandler(Calculator myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if (methodIncludedInMethodsLog(method)) {
                System.out.print("executed method: calculation, params: ");
                for (Object arg : args) System.out.print(arg + " ");
                System.out.println();
            }
            return method.invoke(myClass, args);
        }

        // проверка вхождения метода в список methodsLog
        private boolean methodIncludedInMethodsLog(Method method) {
            for (Method met : methodsLog) {
                if (met.getName().equals(method.getName()) &&
                        Arrays.equals(met.getParameterTypes(), method.getParameterTypes())) {
                    return true;
                }
            }
            return false;
        }

    }
}
