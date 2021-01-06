package ru.otus.gc;

import java.util.Arrays;
import java.util.UUID;

class Benchmark implements BenchmarkMBean {
    private final int loopCounter;
    private volatile int size = 0;
    // строковый массив для заполнения памяти
    private String[] data = new String[0];

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        // переменные для хранения длин массива
        int newLen;
        int oldLen = 0;

        for (int idx = 0; idx < loopCounter; idx++) {
            // увеличиваем длину массива на size
            newLen = oldLen + size;
            data = Arrays.copyOf(data, newLen);
            // заполняем новые элементы массива
            for (int i = oldLen; i < newLen; i++) {
                data[i] = UUID.randomUUID().toString();
            }
            // уменьшаем длину массива на size/2
            oldLen = newLen - size / 2;
            data = Arrays.copyOf(data, oldLen);

            System.out.println("Cycle " + idx);
            Thread.sleep(50); //Label_1
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }
}
