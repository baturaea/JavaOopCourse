package ru.baturaea2022.matrix;

import ru.baturaea2022.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] componentsArray;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("matrix dimension can't be <= 0");
        }

        componentsArray = new Vector[n];
        for (int i = 0; i < n; i++) {
            componentsArray[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        this.componentsArray = Arrays.copyOf(matrix.componentsArray, matrix.componentsArray.length);
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Matrix dimension = " + array.length + " is incorrect");
        }

        componentsArray = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            componentsArray[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Matrix dimension = " + array.length + " is incorrect");
        }

        componentsArray = new Vector[array.length];
        componentsArray = Arrays.copyOf(array, array.length);
        //System.arraycopy(array, 0, componentsMatrix, 0, array.length);
    }

    private Matrix getMatrixMinor(Vector[] vectorsArray, int index) {
        Matrix resultMatrix = new Matrix(vectorsArray.length - 1, vectorsArray.length - 1);
        int matrixIndex = 0;

        for (int i = 0; i < vectorsArray.length; i++) {
            if (i == index) {
                continue;
            }
            Vector vectorMinor = new Vector(vectorsArray.length - 1);
            int vectorIndex = 0;

            for (int j = 0; j < vectorsArray.length; j++) {
                if (j == index) {
                    continue;
                }
                vectorMinor.setComponent(vectorIndex, vectorsArray[i].getComponent(j));
                vectorIndex++;
            }

            resultMatrix.setRowVector(matrixIndex, vectorMinor);
            matrixIndex++;
        }

        return resultMatrix;
    }

    public int[] getSize() {
        return new int[]{componentsArray.length, componentsArray[0].getSize()};
    }

    public Vector getRowVector(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= componentsArray.length) {
            throw new ArrayIndexOutOfBoundsException("Component index = " + rowIndex
                    + ", must be 0 or greater but less " + componentsArray.length);
        }

        return componentsArray[rowIndex];
    }

    public void setRowVector(int rowIndex, Vector vector) {
        if (rowIndex < 0 || rowIndex >= componentsArray.length) {
            throw new IllegalArgumentException("Component index = " + rowIndex
                    + " must be 0 or greater but less " + componentsArray.length);
        }

        componentsArray[rowIndex] = vector;
    }

    public Vector getColumnVector(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= componentsArray[0].getSize()) {
            throw new IllegalArgumentException("Component index = " + columnIndex
                    + ", must be 0 or greater but less " + componentsArray[0].getSize());
        }

        Vector vector = new Vector(componentsArray[0].getSize());
        for (int i = 0; i < componentsArray.length; i++) {
            vector.setComponent(i, componentsArray[i].getComponent(columnIndex));
        }
        return vector;
    }

    public void Transposition() {
        Vector[] tmpComponents = new Vector[componentsArray[0].getSize()];

        for (int i = 0; i < componentsArray[0].getSize(); i++) {
            tmpComponents[i] = getColumnVector(i);
        }

        componentsArray = tmpComponents;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector v : componentsArray) {
            v.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (getSize()[1] != getSize()[0]) {
            throw new IllegalArgumentException("The number of columns = " + getSize()[1]
                    + " must be equal to the number of rows = " + getSize()[0]);
        }

        if (getSize()[0] == 2) {
            return componentsArray[0].getComponent(0) * componentsArray[1].getComponent(1)
                    - componentsArray[0].getComponent(1) * componentsArray[1].getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < componentsArray[0].getSize(); i++) {
            determinant += Math.pow(-1, i + 1) * componentsArray[0].getComponent(i)
                    * getMatrixMinor(componentsArray, i).getDeterminant();
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument vector = null");
        }

        if (vector.getSize() != componentsArray[0].getSize()) {
            throw new IllegalArgumentException("Vector size = " + vector.getSize()
                    + " must be equal to the number of matrix columns: " + componentsArray[0].getSize());
        }

        Vector resultVector = new Vector(vector.getSize());
        double result = 0;

        for (int i = 0; i < componentsArray.length; i++) {
            for (int j = 0; j < vector.getLength(); j++) {
                result += vector.getComponent(j) * componentsArray[i].getComponent(j);
            }

            resultVector.setComponent(i, result);
        }

        return resultVector;
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        if (componentsArray.length == matrix.componentsArray.length
                && componentsArray[0].getSize() == matrix.componentsArray[0].getSize()) {
            for (int i = 0; i < componentsArray.length; i++) {
                componentsArray[i].add(matrix.getRowVector(i));
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Wrong argument matrix = null");
        }

        if (componentsArray.length == matrix.componentsArray.length
                && componentsArray[0].getSize() == matrix.componentsArray[0].getSize()) {
            for (int i = 0; i < componentsArray.length; i++) {
                componentsArray[i].subtract(matrix.getRowVector(i));
            }
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Wrong argument matrix1 = null");
        }

        if (matrix2 == null) {
            throw new IllegalArgumentException("Wrong argument matrix2 = null");
        }

        int dimension = matrix1.getSize()[1];
        if (dimension != matrix2.getSize()[0]) {
            throw new IllegalArgumentException("The number of columns of matrix1 = " + dimension
                    + " must be equal to the number of rows of matrix2 = " + matrix2.getSize()[0]);
        }

        Matrix resultMatrix = new Matrix(matrix1.getSize()[0], matrix2.getSize()[1]);

        for (int i = 0; i < matrix1.getSize()[0]; i++) {
            Vector resultVector = new Vector(dimension);
            Vector v1 = matrix1.getRowVector(i);

            for (int j = 0; j < matrix2.getSize()[1]; j++) {
                Vector v2 = matrix2.getColumnVector(j);

                double result = 0;

                for (int k = 0; k < v1.getSize(); k++) {
                    result += v1.getComponent(k) * v2.getComponent(k);
                }

                resultVector.setComponent(j, result);
            }

            resultMatrix.setRowVector(i, resultVector);
        }

        return resultMatrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < componentsArray.length - 1; i++) {
            builder.append(componentsArray[i].toString());
            builder.append(", ");
        }

        builder.append(componentsArray[componentsArray.length - 1].toString());
        builder.append("}");

        return builder.toString();
    }
}