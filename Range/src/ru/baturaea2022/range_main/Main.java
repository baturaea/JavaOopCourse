package ru.baturaea2022.range_main;

import ru.baturaea2022.range.Range;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Укажите начало диапазона: ");
        double fromNumber = scanner.nextDouble();

        System.out.print("Укажите конец диапазона: ");
        double toNumber = scanner.nextDouble();

        System.out.print("Укажите число для проверки вхождения в диапазон: ");
        double checkNumber = scanner.nextDouble();

        Range range = new Range(fromNumber, toNumber);
        System.out.printf("Длинна диапазона от %.2f до %.2f равна %.2f%n", range.getFrom(), range.getTo(), range.getLength());
        String checkingResult = range.isInside(checkNumber) ? "входит в указанный диапазон" : "не входит в указанный диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, checkingResult);

        System.out.print("Измените начало диапазона: ");
        range.setFrom(scanner.nextDouble());

        System.out.print("Измените конец диапазона: ");
        range.setTo(scanner.nextDouble());

        System.out.printf("Длинна нового диапазона от %.2f до %.2f равна %.2f%n", range.getFrom(), range.getTo(), range.getLength());
        checkingResult = range.isInside(checkNumber) ? "входит в новый диапазон" : "не входит в новый диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, checkingResult);    }
}
