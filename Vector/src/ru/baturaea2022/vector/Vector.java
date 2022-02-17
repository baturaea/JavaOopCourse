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
            throw new IllegalArgumentException("Wrong argument " + null);
        }

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array size is incorrect - " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double... array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vector size is incorrect - " + size);
        }

        components = new double[size];
        if (array.length > 0) {
            components = Arrays.copyOf(array, array.length);
        }
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Component index = " + index
                    + ", must be 0 or greater but less " + components.length);
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Component index = " + index
                    + " must be 0 or greater but less " + components.length);
        }

        components[index] = component;
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument");
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(vector.components.length, components.length); i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Wrong argument");
        }

        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < Math.min(vector.components.length, components.length); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void expand() {
        this.multiplyScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double c : components) {
            sum += c * c;
        }

        return Math.sqrt(sum);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class
                || vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("Wrong argument");
        }

        Vector resultVector = new Vector(vector1.getSize() > vector2.getSize() ? vector1 : vector2);
        resultVector.add(vector1.getSize() > vector2.getSize() ? vector2 : vector1);

        return resultVector;
    }

    public static Vector getDiff(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class
                || vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("Wrong first argument");
        }

        Vector resultVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        resultVector.add(vector2);
        resultVector.subtract(vector1);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class
                || vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("Wrong first argument");
        }

        double scalar = 0;

        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            scalar += vector1.components[i] * vector2.components[i];
        }

        return scalar;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");

        for (int i = 0; i < components.length - 1; i++) {
            str.append(components[i]);
            str.append(",");
        }
        str.append(components[components.length - 1]);
        str.append("}");

        return str.toString();
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