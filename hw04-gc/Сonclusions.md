##Выводы:

Использовал два GC: G1, Parallel

Запускал c параметрами:<br>
-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseG1GC<br>
-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:+UseParallelGC

Сравнивал по времени работы приложения и времени работы GC

### Результаты:
Параметр | G1GC | ParallelGC
--- | --- | ---
Время работы приложения (time), сек | 24.957 | 10.520
Время работы GC (gc_time), сек | 11.142 | 0.626
Отношение gc_time/time, %  | 44.645% | 5.95057%

Лучший результат по времени работы приложения показал сборщик мусора ParallelGC (~ в 2.5 раза быстрее чем с G1).
Также время работы самого GC на порядок меньше у ParallelGC.

Таким образом, для данного приложения предпочтительнее сборщик мусора ParallelGC. 

OutOfMemory (java.lang.OutOfMemoryError: Java heap space) достигается путем добавления модификатора 'static' в классе TestList:
 private static List<Double> list = new ArrayList<>();