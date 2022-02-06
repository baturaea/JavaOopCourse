package ru.baturaea2022.vector;

import java.util.Arrays;

public class Vector {

    private double[] componentsVector;

    public static Vector getSumVectors(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong first argument");
        } else if (vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong second argument");
        }

        Vector finalVector = new Vector(vector1.getSize() > vector2.getSize() ? vector1 : vector2);
        finalVector.addVector(vector1.getSize() > vector2.getSize() ? vector2 : vector1);

        return finalVector;
    }

    public static Vector getSubtractVectors(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong first argument");
        } else if (vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong second argument");
        }

        Vector finalVector = new Vector(Math.max(vector1.getSize(), vector2.getSize()));

        finalVector.addVector(vector2);
        finalVector.reversalVector();
        finalVector.addVector(vector1);

        return finalVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null || vector1.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong first argument");
        } else if (vector2 == null || vector2.getClass() != Vector.class) {
            throw new IllegalArgumentException("wrong second argument");
        }

        double scalar = 0;

        for (int i = 0; i < vector1.componentsVector.length; i++) {
            if (vector2.componentsVector.length > i) {
                scalar += vector1.componentsVector[i] * vector2.componentsVector[i];
            }
        }

        return scalar;
    }

    public double getComponentVector(int n) {
        if (n < 0 || n >= componentsVector.length) {
            throw new IllegalArgumentException("incorrect component index");
        }

        return componentsVector[n];
    }

    public void setComponentVector(int n, double component) {
        if (n < 0 || n >= componentsVector.length) {
            throw new IllegalArgumentException("incorrect component index");
        }
        componentsVector[n] = component;
    }

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("vector dimension can't be <= 0");
        }

        componentsVector = new double[n];
        for (int i = 0; i < n; i++) {
            componentsVector[i] = 0;
        }
    }

    public Vector(Vector vector) {
        if (vector == null || vector.getClass() != getClass()) {
            throw new IllegalArgumentException("wrong argument");
        }
        this.componentsVector = Arrays.copyOf(vector.componentsVector, vector.componentsVector.length);

        /* альтернативное, "ручное" копирование
        this.componentsVector = new double[vector.componentsVector.length];
        for (int i = 0; i < vector.componentsVector.length; i++) {
            componentsVector[i] = vector.componentsVector[i];
        }*/
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("vector dimension is incorrect");
        }

        componentsVector = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double... array) {
        if (n <= 0) {
            throw new IllegalArgumentException("vector dimension can't be <= 0");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("vector dimension is incorrect");
        }

        componentsVector = new double[n];
        for (int i = 0; i < n; i++) {
            if (array.length > i) {
                componentsVector[i] = array[i];
            } else {
                componentsVector[i] = 0;
            }
        }
    }

    public int getSize() {
        return componentsVector.length;
    }

    public void addVector(Vector vector) {
        if (vector == null || vector.getClass() != getClass()) {
            throw new IllegalArgumentException("wrong argument");
        }

        for (int i = 0; i < componentsVector.length; i++) {
            if (vector.componentsVector.length > i) {
                this.componentsVector[i] += vector.componentsVector[i];
            }
        }
    }

    public void subtractVector(Vector vector) {
        if (vector == null || vector.getClass() != getClass()) {
            throw new IllegalArgumentException("wrong argument");
        }

        for (int i = 0; i < componentsVector.length; i++) {
            this.componentsVector[i] -= vector.componentsVector[i];
        }
    }

    public void multiplyVector(double scalar) {
        for (int i = 0; i < componentsVector.length; i++) {
            componentsVector[i] *= scalar;
        }
    }

    public void reversalVector() {
        for (int i = 0; i < componentsVector.length; i++) {
            componentsVector[i] *= -1;
        }
    }

    public double getLengthVector() {
        double sum = 0;
        for (double v : componentsVector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return Arrays.toString(componentsVector).replace('[', '{').replace(']', '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Arrays.equals(componentsVector, vector.componentsVector);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(componentsVector);
    }
}