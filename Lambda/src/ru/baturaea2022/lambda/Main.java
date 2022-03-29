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
        List<String> uniqueNameList = personsList.stream()
                .map(Person::getName)
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());

        // Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.
        System.out.println();
        System.out.println("Уникальные имена: " + String.join(", ", uniqueNameList));

        // В) получить список людей младше 18, посчитать для них средний возраст
        System.out.println();
        List<Person> youngPersonsList = personsList.stream()
                .filter(p -> p.getAge() < 18)
                .peek(person -> System.out.println("Name: " + person.getName() + " Age: " + person.getAge()))
                .collect(Collectors.toList());

        System.out.printf("Средний возраст людей моложе 18 = %.0f %n", youngPersonsList.stream()
                .mapToDouble(Person::getAge)
                .average().orElse(0.0));

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст
        System.out.println();
        Map<String, Double> averageAgeByName = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        averageAgeByName.forEach((name, age) ->
                System.out.printf("У людей с именем - %s , средний возраст = %.0f%n", name, age));

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста
        System.out.println();
        List<Person> rangeAgePersonsList = personsList.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .filter(p -> p.getAge() > 19)
                .filter(p -> p.getAge() < 45)
                .peek(person -> System.out.println("Name: " + person.getName() + " Age: " + person.getAge()))
                .collect(Collectors.toList());
    }
}