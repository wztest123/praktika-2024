Программа на Java для обработки данных
Основные шаги:
Создание классов и методов для обработки данных.
Чтение данных из файла.
Обработка данных (сортировка, фильтрация).
Сохранение данных в файл.
Ввод данных пользователем.

## Класс Person для представления данных

```public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}
```

## Класс DataProcessor для обработки данных

```
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor {
    private List<Person> people;

    public DataProcessor() {
        this.people = new ArrayList<>();
    }

    // Чтение данных из файла
    public void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    people.add(new Person(name, age));
                }
            }
        }
    }

    // Запись данных в файл
    public void writeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
        }
    }

    // Сортировка по возрасту
    public void sortByAge() {
        people.sort(Comparator.comparingInt(Person::getAge));
    }

    // Фильтрация по возрасту
    public List<Person> filterByAge(int minAge) {
        return people.stream()
                     .filter(person -> person.getAge() >= minAge)
                     .collect(Collectors.toList());
    }

    // Вывод данных
    public void printData() {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    // Добавление нового человека
    public void addPerson(String name, int age) {
        people.add(new Person(name, age));
    }
}
```

## Основной класс c пользовательским вводом

```
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataProcessor processor = new DataProcessor();
        
        try {
            // Чтение данных из файла
            processor.readFromFile("people.txt");
            
            // Вывод исходных данных
            System.out.println("Исходные данные:");
            processor.printData();

            // Сортировка по возрасту
            processor.sortByAge();
            System.out.println("\nДанные после сортировки по возрасту:");
            processor.printData();

            // Фильтрация по возрасту
            System.out.print("\nВведите минимальный возраст для фильтрации: ");
            int minAge = scanner.nextInt();
            scanner.nextLine();  // consume newline
            System.out.println("\nОтфильтрованные данные:");
            processor.filterByAge(minAge).forEach(System.out::println);

            // Ввод новых данных
            System.out.print("\nВведите имя нового человека: ");
            String name = scanner.nextLine();
            System.out.print("Введите возраст нового человека: ");
            int age = scanner.nextInt();
            processor.addPerson(name, age);

            // Сохранение данных в файл
            processor.writeToFile("people.txt");
            System.out.println("\nНовые данные сохранены в файл.");

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}
```
## Подробный анализ по выполненной задаче

Классы и объектно-ориентированный подход:

Класс Person представляет данные о человеке.
Класс *DataProcessor* содержит методы для чтения, записи, сортировки и фильтрации данных.
Классы инкапсулируют данные и операции над ними, улучшая структуру и читаемость кода.
Чтение данных:

Используется *BufferedReader* для чтения данных из файла.
Данные парсятся и создаются объекты *Person*.
Обработка данных:

Метод *sortByAge* сортирует список people по возрасту.
Метод *filterByAge* фильтрует людей по возрасту, возвращая отфильтрованный список.
Сохранение данных:

Метод *writeToFile* записывает данные в файл, форматируя каждый объект Person в строку.
Ввод данных пользователем:

Пользователь может ввести минимальный возраст для фильтрации и данные для нового человека.
Введенные данные добавляются и сохраняются в файл.
Обработка ошибок:

Программа обрабатывает исключения ввода/вывода, информируя пользователя о возможных ошибках.
Программа легко расширяется:

Возможность добавления новых методов и функциональности, например, дополнительной фильтрации или сортировки.