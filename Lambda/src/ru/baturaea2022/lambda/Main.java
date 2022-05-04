package ru.baturaea2022.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = Arrays.asList(
                new Person("Иван", 21),
                new Person("Петр", 12),
                new Person("Сергей", 48),
                new Person("Максим", 56),
                new Person("Егор", 25),
                new Person("Александр", 10),
                new Person("Влад", 30),
                new Person("Александр", 18),
                new Person("Иван", 33),
                new Person("Семён", 16)
        );

        // А) получить список уникальных имен
        String uniqueNameList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "", "."));

        // Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
        System.out.println();
        System.out.println("Уникальные имена: " + uniqueNameList);

        // В) получить список людей младше 18, посчитать для них средний возраст
        System.out.println();
        List<Person> youngPersonsList = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.toList());
        System.out.println("Список людей младше 18: " + youngPersonsList);
        double youngPersonsLis = youngPersonsList.stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.printf("Средний возраст людей моложе 18 = %.0f %n", youngPersonsLis);

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        System.out.println();
        Map<String, Double> averageAgeByName = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        averageAgeByName.forEach((name, age) ->
                System.out.printf("У людей с именем - %s , средний возраст = %.0f%n", name, age));

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        System.out.println();
        List<Person> rangeAgePersonsList = personsList.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        System.out.println("Список людей, возраст которых от 20 до 45: " + rangeAgePersonsList);
    }
}