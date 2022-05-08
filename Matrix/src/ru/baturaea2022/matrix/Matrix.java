package ru.baturaea2022.matrix;

import ru.baturaea2022.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int rows, int columns) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Argument rows = " + rows + " can't be <= 0");
        }

        if (columns <= 0) {
            throw new IllegalArgumentException("Argument rows = " + columns + " can't be <= 0");
        }

        matrixRows = new Vector[rows];

        for (int i = 0; i < rows; i++) {
            matrixRows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        matrixRows = Arrays.copyOf(matrix.matrixRows, matrix.matrixRows.length);
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Matrix dimension = 0 is incorrect");
        }

        int maxSizeColumn = 0;

        for (double[] doubles : array) {
            maxSizeColumn = Math.max(maxSizeColumn, doubles.length);
        }

        matrixRows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(maxSizeColumn, array[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Matrix dimension = " + array.length + " is incorrect");
        }

        int maxSizeColumn = 0;

        for (Vector vector : array) {
            maxSizeColumn = Math.max(maxSizeColumn, vector.getSize());
        }

        matrixRows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(maxSizeColumn);
            matrixRows[i].add(array[i]);
        }


    }

    public int getRowsSize() {
        return matrixRows.length;
    }

    public int getColumnsSize() {
        return matrixRows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= matrixRows.length) {
            throw new IndexOutOfBoundsException("Argument rowIndex = " + rowIndex
                    + ", must be 0 or greater but less " + matrixRows.length);
        }

        Vector vector = new Vector(getColumnsSize());
        for (int i = 0; i < getColumnsSize(); i++) {
            vector.setComponent(i, matrixRows[rowIndex].getComponent(i));
        }

        return vector;
    }

    public void setRow(int rowIndex, Vector vector) {
        if (rowIndex < 0 || rowIndex >= getRowsSize()) {
            throw new IndexOutOfBoundsException("Argument rowIndex = " + rowIndex
                    + " must be 0 or greater but less " + getRowsSize());
        }

        if (vector.getSize() != getColumnsSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize()
                    + " must be equal to the number of matrix columns: " + getColumnsSize());
        }

        for (int i = 0; i < getColumnsSize(); i++) {
            matrixRows[rowIndex].setComponent(i, vector.getComponent(i));
        }
    }

    public Vector getColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= getColumnsSize()) {
            throw new IndexOutOfBoundsException("Argument columnIndex = " + columnIndex
                    + ", must be 0 or greater but less " + getColumnsSize());
        }

        Vector vector = new Vector(getRowsSize());

        for (int i = 0; i < getRowsSize(); i++) {
            vector.setComponent(i, matrixRows[i].getComponent(columnIndex));
        }

        return vector;
    }

    public void transpose() {
        Vector[] transposedColumns = new Vector[getColumnsSize()];

        for (int i = 0; i < transposedColumns.length; i++) {
            transposedColumns[i] = getColumn(i);
        }

        matrixRows = transposedColumns;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : matrixRows) {
            v.multiplyByScalar(scalar);
        }
    }

    static private Matrix getMatrixMinor(Matrix matrix, int index) {
        Matrix resultMatrix = new Matrix(matrix.getRowsSize() - 1, matrix.getRowsSize() - 1);
        int matrixIndex = 0;

        for (int i = 1; i < matrix.getRowsSize(); i++) {
            Vector vectorMinor = new Vector(matrix.getRowsSize() - 1);
            int vectorIndex = 0;

            for (int j = 0; j < matrix.getRowsSize(); j++) {
                if (j == index) {
                    continue;
                }

                vectorMinor.setComponent(vectorIndex, matrix.getRow(i).getComponent(j));
                vectorIndex++;
            }

            resultMatrix.setRow(matrixIndex, vectorMinor);
            matrixIndex++;
        }

        return resultMatrix;
    }

    public double getDeterminant() {
        if (getColumnsSize() != getRowsSize()) {
            throw new UnsupportedOperationException("The number of columns = " + getColumnsSize()
                    + " must be equal to the number of rows = " + getRowsSize());
        }

        if (getRowsSize() == 2) {
            return matrixRows[0].getComponent(0) * matrixRows[1].getComponent(1)
                    - matrixRows[0].getComponent(1) * matrixRows[1].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < matrixRows[0].getSize(); i++) {
            determinant += Math.pow(-1, i) * matrixRows[0].getComponent(i)
                    * Matrix.getMatrixMinor(this, i).getDeterminant();
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument vector = null");
        }

        if (vector.getSize() != getColumnsSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize()
                    + " must be equal to the number of matrix columns: " + getColumnsSize());
        }

        Vector resultVector = new Vector(matrixRows.length);

        for (int i = 0; i < matrixRows.length; i++) {
            double result = 0;

            for (int j = 0; j < matrixRows[0].getSize(); j++) {
                result += vector.getComponent(j) * matrixRows[i].getComponent(j);
            }

            resultVector.setComponent(i, result);
        }

        return resultVector;
    }

    private static boolean checkMatrixDimensions(Matrix matrix1, Matrix matrix2) {
        return matrix1.getRowsSize() != matrix2.getRowsSize()
                || matrix1.getColumnsSize() != matrix2.getColumnsSize();
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        if (checkMatrixDimensions(this, matrix)) {
            throw new IllegalArgumentException("Matrices of different dimensions");
        }

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].add(matrix.matrixRows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        if (checkMatrixDimensions(this, matrix)) {
            throw new IllegalArgumentException("Matrices of different dimensions");
        }

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].subtract(matrix.matrixRows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        if (checkMatrixDimensions(matrix1, matrix2)) {
            throw new IllegalArgumentException("Matrices of different dimensions");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < matrix1.getRowsSize(); i++) {
            matrix1.matrixRows[i].add(matrix2.matrixRows[i]);
        }

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        if (checkMatrixDimensions(matrix1, matrix2)) {
            throw new IllegalArgumentException("Matrices of different dimensions");
        }

        Matrix resultMatrix = new Matrix(matrix1);

        for (int i = 0; i < matrix1.getRowsSize(); i++) {
            matrix1.matrixRows[i].subtract(matrix2.matrixRows[i]);
        }

        return resultMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        int generalDimension = matrix1.getColumnsSize();
        if (generalDimension != matrix2.getRowsSize()) {
            throw new IllegalArgumentException("The number of columns of matrix1 = " + generalDimension
                    + " must be equal to the number of rows of matrix2 = " + matrix2.getRowsSize());
        }

        Matrix resultMatrix = new Matrix(matrix1.getRowsSize(), matrix2.getColumnsSize());

        for (int i = 0; i < matrix1.getRowsSize(); i++) {
            Vector resultVector = new Vector(generalDimension);

            for (int j = 0; j < matrix2.getColumnsSize(); j++) {
                Vector columnVector = matrix2.getColumn(j);

                resultVector.setComponent(j, Vector.getScalarProduct(matrix1.matrixRows[i], columnVector));
            }

            resultMatrix.setRow(i, resultVector);
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < matrixRows.length - 1; i++) {
            builder.append(matrixRows[i]);
            builder.append(", ");
        }

        builder.append(matrixRows[matrixRows.length - 1]);
        builder.append("}");

        return builder.toString();
    }
}