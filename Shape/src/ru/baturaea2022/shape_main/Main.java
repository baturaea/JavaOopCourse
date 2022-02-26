package ru.baturaea2022.shape_main;

import ru.baturaea2022.shape.*;

import java.util.Arrays;

public class Main {
    public static Shape getMaxAreaShape(Shape[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array can’t be empty");
        }

        Arrays.sort(array, new AreaComparator());

        //Альтернативная сортировка
        //Arrays.sort(array, Comparator.comparing(Shape::getArea));

        return array[array.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Array must have more than 2 elements " + Arrays.toString(array));
        }

        Arrays.sort(array, new PerimeterComparator());

        //Альтернативная сортировка
        //Arrays.sort(array, Comparator.comparing(Shape::getPerimeter));

        return array[array.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapesArray = {
                new Square(1.2),
                new Triangle(3, 3, 5, 0, -2, -5),
                new Rectangle(3.1, 8.7),
                new Circle(2.3),
                new Square(6.5),
                new Triangle(0, 5, -5, 0, 5, -1),
                new Rectangle(10.1, 2.7),
                new Circle(4.3)
        };

        System.out.println("===================");
        System.out.println("Площади фигур, getArea():");

        for (Shape e : shapesArray) {
            System.out.printf("%.2f%n", e.getArea());
        }

        System.out.println("===================");
        Shape shape1 = getMaxAreaShape(shapesArray);
        System.out.printf("Фигура с максимальной площадью: %s%n", shape1);

        System.out.println("===================");
        System.out.println("Периметры фигур, getPerimeter():");

        for (Shape e : shapesArray) {
            System.out.printf("%.2f%n", e.getPerimeter());
        }

        System.out.println("===================");
        Shape shape2 = getSecondPerimeterShape(shapesArray);
        System.out.printf("Фигура со вторым по величине периметром = %f у фигуры: %s%n", shape2.getPerimeter(), shape2);

        System.out.println("===================");
        System.out.println("Переопределенный toString():");

        for (Shape e : shapesArray) {
            System.out.printf("%s%n", e);
        }

        System.out.println("===================");
        System.out.println("Переопределенный hashCode():");

        for (Shape e : shapesArray) {
            System.out.printf("%d%n", e.hashCode());
        }

        System.out.println("===================");
        System.out.println("Переопределенный equals():");
        System.out.printf(" shape1.equals(shapesArray[7]) = %b%n shape2.equals(shapesArray[7]) = %b",
                shape1.equals(shapesArray[7]), shape2.equals(shapesArray[7]));
    }
}