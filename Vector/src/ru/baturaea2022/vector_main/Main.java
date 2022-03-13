package ru.baturaea2022.vector_main;

import ru.baturaea2022.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {0.5, 2.0, 3.0};
        Vector vector1 = new Vector(4);
        Vector vector2 = new Vector(4, 2.1, 1.5, 4);
        Vector vector3 = new Vector(array);
        Vector vector4 = new Vector(vector3);

        System.out.println("===================toString()");
        System.out.printf("vector1 - %s%n", vector1);
        System.out.printf("vector2 - %s%n", vector2);
        System.out.printf("vector3 - %s%n", vector3);
        System.out.printf("vector4 - %s%n", vector4);

        System.out.println("===================equals");
        System.out.printf("vector3.equals(vector4) - %b%n", vector3.equals(vector4));
        System.out.printf("vector3.equals(vector2) - %b%n", vector3.equals(vector2));

        System.out.println("===================vector3.getSize()");
        System.out.printf("%d%n", vector3.getSize());

        System.out.println("===================vector2.addVector(vector3)");
        vector2.add(vector3);
        System.out.printf("%s%n", vector2);

        System.out.println("===================vector3.reversalVector()");
        vector3.rotate();
        System.out.printf("%s%n", vector3);
        System.out.println("===================vector3.multiplyVector(2)");
        vector3.multiplyByScalar(2);
        System.out.printf("Результат умножения равен %s%n", vector3);
        System.out.println("===================vector2.subtractVector(vector3)");
        vector2.subtract(vector3);
        System.out.printf("Результат вычитания равен %s%n", vector2);

        System.out.println("===================vector3.getLengthVector()");
        System.out.printf("%f%n", vector3.getLength());

        System.out.println("===================static methods");
        Vector vector5 = Vector.getSum(vector1, vector3);
        System.out.printf("getSumVectors(vector1 = %s, vector3 = %s) = %s%n", vector1, vector3, vector5);

        Vector vector6 = Vector.getDifference(vector3, vector2);
        System.out.printf("getSubtractVectors(vector3 = %s, vector2 = %s) = %s%n", vector3, vector2, vector6);

        System.out.println("getScalarProduct");
        System.out.printf("Скалярное произведение vector1 = %s и vector2 = %s равно %f%n",
                vector1, vector2, Vector.getScalarProduct(vector1, vector2));
        System.out.printf("Скалярное произведение vector5 = %s и vector4 = %s равно %f%n",
                vector5, vector4, Vector.getScalarProduct(vector5, vector4));
    }
}