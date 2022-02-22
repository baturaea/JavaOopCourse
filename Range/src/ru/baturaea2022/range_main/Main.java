package ru.baturaea2022.range_main;

import ru.baturaea2022.range.Range;

import java.util.Scanner;

public class Main {
    private static String rangeArrayStr(Range[] array) {
        StringBuilder str = new StringBuilder("[");
        if (array.length != 0) {
            for (int i = 0; i < array.length - 1; i++) {
                str.append(array[i]);
                str.append(",");
            }

            str.append(array[array.length - 1]);
        }

        str.append("]");

        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Укажите начало диапазона: ");
        double from = scanner.nextDouble();

        System.out.print("Укажите конец диапазона: ");
        double to = scanner.nextDouble();

        System.out.print("Укажите число для проверки вхождения в диапазон: ");
        double checkNumber = scanner.nextDouble();

        Range range1 = new Range(from, to);
        System.out.printf("Длинна диапазона от %.2f до %.2f равна %.2f%n", range1.getFrom(), range1.getTo(), range1.getLength());
        String isInside = range1.isInside(checkNumber) ? "входит в указанный диапазон" : "не входит в указанный диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, isInside);

        System.out.print("Измените начало диапазона: ");
        range1.setFrom(scanner.nextDouble());

        System.out.print("Измените конец диапазона: ");
        range1.setTo(scanner.nextDouble());

        System.out.printf("Длинна нового диапазона от %.2f до %.2f равна %.2f%n", range1.getFrom(), range1.getTo(), range1.getLength());
        isInside = range1.isInside(checkNumber) ? "входит в новый диапазон" : "не входит в новый диапазон";
        System.out.printf("Число %.2f %s%n", checkNumber, isInside);

        Range range2 = new Range(4, 7);
        Range range3 = new Range(4, 6);

        System.out.printf("range2 - %s, range3 - %s%n", range2, range3);

        System.out.println("+++++++++check getIntersection+++++++++");
        for (int i = 0; i < 7; i++) {
            Range rangeI = new Range(2 + i, 3 + i);
            System.out.printf("range2 = %s; rangeI = %s%n", range2, rangeI);
            System.out.printf("Intersection - %s%n", range2.getIntersection(rangeI));
        }

        System.out.println("+++++++++check getUnion+++++++++");
        for (int i = 1; i < 8; i++) {
            Range rangeI = new Range(1 + i, 2 + i);
            System.out.printf("range2 = %s; rangeI = %s%n", range2, rangeI);
            System.out.printf("Union - %s%n", rangeArrayStr(range2.getUnion(rangeI)));
        }

        System.out.println("+++++++++check getDifference+++++++++");
        for (int i = 0; i < 9; i++) {
            Range rangeI = new Range(i, i + 3);
            System.out.printf("range2 = %s; rangeI = %s%n", range2, rangeI);
            System.out.printf("Difference - %s%n", rangeArrayStr(range2.getDifference(rangeI)));
        }
    }
}