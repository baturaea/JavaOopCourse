package ru.baturaea2022.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size = " + size + " can't be <= 0");
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument vector = null.");
        }

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array size = " + array.length + ", can't be = 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double... array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size = " + size + " can't be <= 0");
        }

        components = Arrays.copyOf(array, size);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Component index = " + index
                    + ", must be 0 or greater but less " + components.length);
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Component index = " + index
                    + " must be 0 or greater but less " + components.length);
        }

        components[index] = component;
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument vector = null.");
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] += vector.components[i];
        }

    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument vector = null.");
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(components.length, vector.components.length); i++) {
            components[i] -= vector.components[i];
        }

    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void rotate() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double c : components) {
            sum += c * c;
        }

        return Math.sqrt(sum);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Wrong argument vector1 = null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Wrong argument vector2 = null");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Wrong argument vector1 = null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Wrong argument vector2 = null");
        }

        Vector resultVector = new Vector(vector1);
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new IllegalArgumentException("Wrong argument vector1 = null");
        }

        if (vector2 == null) {
            throw new IllegalArgumentException("Wrong argument vector2 = null");
        }

        double scalar = 0;
        int minLength = Math.min(vector1.components.length, vector2.components.length);

        for (int i = 0; i < minLength; i++) {
            scalar += vector1.components[i] * vector2.components[i];
        }

        return scalar;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");

        for (int i = 0; i < components.length - 1; i++) {
            builder.append(components[i]);
            builder.append(", ");
        }

        builder.append(components[components.length - 1]);
        builder.append("}");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}