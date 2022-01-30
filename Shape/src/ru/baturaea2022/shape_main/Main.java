package ru.baturaea2022.shape_main;

import ru.baturaea2022.shape.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shape getMaxAreaShape(Shape[] array) {
        Comparator<Shape> comparatorByArea = Comparator.comparing(Shape::getArea);
        Arrays.sort(array, comparatorByArea);

        return array[array.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] array) {
        Comparator<Shape> comparatorByPerimeter = Comparator.comparing(Shape::getPerimeter);
        Arrays.sort(array, comparatorByPerimeter);

        return array[array.length - 2];
    }

    public static void main(String[] args) {
        Shape[] arrayShapes = new Shape[8];

        arrayShapes[0] = new Square(15.2);
        arrayShapes[1] = new Triangle(1, 1, 5, 0, -2, -5);
        arrayShapes[2] = new Rectangle(3.1, 8.7);
        arrayShapes[3] = new Circle(2.3);
        arrayShapes[4] = new Square(6.5);
        arrayShapes[5] = new Triangle(0, 5, -5, 0, 5, -1);
        arrayShapes[6] = new Rectangle(10.1, 2.7);
        arrayShapes[7] = new Circle(4.3);

        System.out.println("Площади фигур, getArea():");
        for (Shape e : arrayShapes) {
            System.out.printf("%.2f%n", e.getArea());
        }

        Shape shape1 = getMaxAreaShape(arrayShapes);
        System.out.printf("Фигура с максимальной площадью: %s%n", shape1.toString());

        System.out.println();
        System.out.println("Периметры фигур, getPerimeter():");
        for (Shape e : arrayShapes) {
            System.out.printf("%.2f%n", e.getPerimeter());
        }

        Shape shape2 = getSecondPerimeterShape(arrayShapes);
        System.out.printf("Фигура со вторым по величине периметром: %s%n", shape2.toString());

        System.out.println("Переопределенный toString():");
        for (Shape e : arrayShapes) {
            System.out.printf("%s%n", e.toString());
        }

        System.out.println("Переопределенный hashCode():");
        for (Shape e : arrayShapes) {
            System.out.printf("%d%n", e.hashCode());
        }

        System.out.println("Переопределенный equals():");
        System.out.printf(" shape1.equals(arrayShapes[7] = %b%n shape2.equals(arrayShapes[7] = %b",
                shape1.equals(arrayShapes[7]), shape2.equals(arrayShapes[7]));
    }
}