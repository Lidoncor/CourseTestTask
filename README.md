# CourseTestTask

Тестовое задание на курс по промышленной разработке на Java

## Требования

- `Java 17.0.10`
- `Apache Maven 3.9.6`

## Описание

Использованные библиотеки:

- `JCommander`
```xml
<!-- https://mvnrepository.com/artifact/com.beust/jcommander -->
<dependency>
    <groupId>com.beust</groupId>
    <artifactId>jcommander</artifactId>
    <version>1.82</version>
</dependency>
```

- `Lombok`
```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
    <scope>provided</scope>
</dependency>
```

Параметры:
- `-o` - Путь для результатов
- `-p` - Префикс имен выходных файлов
- `-a` - Режим добавления в существующие файлы
- `-s` - Краткая статистика
- `-f` - Полная статистика

Вызов:
```shell
java -jar CourseTestTask-1.0.jar -o C:\Users\User\Desktop\results -f -a -p result- in1.txt in2.txt
```

Вывод:
```
type=integers, quantity=2, min=45, max=1234567890123456789, avg=617283945061728417, sum=1234567890123456834
type=floats, quantity=3, min=-0.001, max=3.1415, avg=1.0468333333333335, sum=3.1405000000000003
type=strings, quantity=6, minLength=4, maxLength=42
```

## Сборка

1) В командной строке переходим в папку проекта
2) Запускаем команду `mvn clean package`
3) Ждём сообщения `BUILD SUCCESS`
4) Запускаем программу `java -jar target/CourseTestTask-1.0.jar`