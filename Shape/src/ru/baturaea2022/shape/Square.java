package ru.baturaea2022.shape;

import java.util.Arrays;

public class Square implements Shape {
    private double sideLength;

    public Square(double side) {
        this.sideLength = side;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return this.sideLength;
    }

    @Override
    public double getHeight() {
        return this.sideLength;
    }

    @Override
    public double getArea() {
        return this.sideLength * this.sideLength;
    }

    @Override
    public double getPerimeter() {
        return this.sideLength * 4;
    }

    @Override
    public String toString() {
        return "Квадрат," + Arrays.toString(new double[]{getWidth(), getHeight(), getArea(), getPerimeter()});
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Square p = (Square) o;

        return sideLength == p.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
