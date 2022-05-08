package ru.baturaea2022.matrix_main;

import ru.baturaea2022.matrix.Matrix;
import ru.baturaea2022.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] array = new double[][]{{4.2, 6.3, 3.1}, {7.1, 5.4, 1.5}};
        Vector[] vectors = {
                new Vector(new double[]{1, -2, 3}),
                new Vector(new double[]{4, 0, 6}),
                new Vector(new double[]{-7, 8, 9})
        };

        Matrix m1 = new Matrix(3, 3);
        Matrix m2 = new Matrix(array);
        Matrix m3 = new Matrix(m2);
        Matrix m4 = new Matrix(vectors);

        System.out.println("Matrix m1 = new Matrix(3, 4);");
        System.out.println(m1);
        System.out.println("Matrix m2 = new Matrix(array);");
        System.out.println(m2);
        System.out.println("Matrix m3 = new Matrix(m2)");
        System.out.println(m3);
        System.out.println("Matrix m4 = new Matrix(vectors)");
        System.out.println(m4);

        System.out.println("++++check getDeterminant++++");
        System.out.printf("m4.getDeterminant() = %.2f%n", m4.getDeterminant());

        System.out.println("++++check getSize++++");
        System.out.println("Rows = " + m4.getRowsSize() + "; Columns = " + m4.getColumnsSize());

        System.out.println("++++getRowVector++++");
        Vector vector1 = new Vector(m4.getRow(0));
        System.out.printf("m4.getRowVector(0) = %s%n", vector1);

        System.out.println("++++setRowVector_getColumnVector++++");
        m1.setRow(0, m4.getColumn(2));
        System.out.printf("m1.setRowVector(0, m4.getColumnVector(2) = %s%n", m1);

        System.out.println("++++check getDeterminant++++");
        System.out.printf("m4.getDeterminant() = %.2f%n", m4.getDeterminant());

        System.out.println("++++check transpose++++");
        m2.transpose();
        System.out.printf("m2.transpose() = %s%n", m2);

        System.out.println("++++check getMultiplication++++");
        System.out.printf("getMultiplication m3(2x3), m4(3x3) = %s%n", Matrix.getMultiplication(m3, m4));

        System.out.println("++++check multiplyByVector++++");
        System.out.printf("multiplyByVector m3(%s) and vector%s = %s%n", m3, vectors[0], m3.multiplyByVector(vectors[0]));
    }
}