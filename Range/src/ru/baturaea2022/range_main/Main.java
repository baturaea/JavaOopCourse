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

        Range range1 = new Range(fromNumber, toNumber);
        System.out.printf("Длинна диапазона от %.2f до %.2f равна %.2f%n", range1.getFrom(), range1.getTo(), range1.getLength());
        String checkingResult = range1.isInside(checkNumber) ? "входит в указанный диапазон" : "не входит в указанный диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, checkingResult);

        System.out.print("Измените начало диапазона: ");
        range1.setFrom(scanner.nextDouble());

        System.out.print("Измените конец диапазона: ");
        range1.setTo(scanner.nextDouble());

        System.out.printf("Длинна нового диапазона от %.2f до %.2f равна %.2f%n", range1.getFrom(), range1.getTo(), range1.getLength());
        checkingResult = range1.isInside(checkNumber) ? "входит в новый диапазон" : "не входит в новый диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, checkingResult);

        Range range2 = new Range(1, 2);
        Range range3 = new Range(4, 6);

        System.out.printf("range2 - {%f, %f}, range3 - {%f, %f}%n", range2.getFrom(), range2.getTo(), range3.getFrom(), range3.getTo());

        Range range4 = range2.getIntersection(range3);
        if (range4 != null) {
            System.out.printf("range2.getIntersection(range3) = {%f, %f}%n", range4.getFrom(), range4.getTo());
        } else {
            System.out.println("range2 и range3 не пересекаются => range4 = null");
        }

        System.out.println("range2.getUnion(range3)");
        Range[] arrayRanges1 = range2.getUnion(range3);
        for (Range r : arrayRanges1) {
            System.out.printf("range - {%f, %f}%n", r.getFrom(), r.getTo());
        }

        System.out.println("range2.getDifference(range3)");
        Range[] arrayRanges2 = range2.getDifference(range3);
        for (Range r : arrayRanges2) {
            System.out.printf("range - {%f, %f}%n", r.getFrom(), r.getTo());
        }
    }
}