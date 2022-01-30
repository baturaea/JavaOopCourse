package ru.baturaea2022.shape;

import java.util.Arrays;

public class Rectangle implements Shape {
    private double sideLength1;
    private double sideLength2;

    public Rectangle(double side1, double side2) {
        this.sideLength1 = side1;
        this.sideLength2 = side2;
    }

    public double getSideLength1() {
        return sideLength1;
    }

    public void setSideLength1(double sideLength1) {
        this.sideLength1 = sideLength1;
    }

    public double getSideLength2() {
        return sideLength2;
    }

    public void setSideLength2(double sideLength2) {
        this.sideLength2 = sideLength2;
    }

    @Override
    public double getWidth() {
        return Math.min(sideLength1, sideLength2);
    }

    @Override
    public double getHeight() {
        return Math.max(sideLength1, sideLength2);
    }

    @Override
    public double getArea() {
        return sideLength1 * sideLength2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (sideLength1 + sideLength2);
    }

    @Override
    public String toString() {
        return "Прямоугольник," + Arrays.toString(new double[]{getWidth(), getHeight(), getArea(), getPerimeter()});
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Rectangle p = (Rectangle) o;

        return sideLength1 == p.sideLength1 && sideLength2 == p.sideLength2;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength1);
        hash = prime * hash + Double.hashCode(sideLength2);

        return hash;
    }
}