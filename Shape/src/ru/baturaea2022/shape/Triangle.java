package ru.baturaea2022.shape;

import java.util.Arrays;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double xMin = Math.min(Math.min(x1, x2), x3);
        double xMax = Math.max(Math.max(x1, x2), x3);
        return xMax - xMin;
    }

    @Override
    public double getHeight() {
        double xMin = Math.min(Math.min(y1, y2), y3);
        double xMax = Math.max(Math.max(y1, y2), y3);

        return xMax - xMin;
    }

    @Override
    public double getPerimeter() {
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return triangleSide1Length + triangleSide2Length + triangleSide3Length;
    }

    @Override
    public double getArea() {
        double triangleSide1Length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double triangleSide2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double triangleSide3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double semiPerimeter = (triangleSide1Length + triangleSide2Length + triangleSide3Length) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - triangleSide1Length) *
                (semiPerimeter - triangleSide2Length) * (semiPerimeter - triangleSide3Length));
    }

    @Override
    public String toString() {
        return "Треугольник," + Arrays.toString(new double[]{getWidth(), getHeight(), getArea(), getPerimeter()});
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Triangle p = (Triangle) o;

        return x1 == p.x1 && y1 == p.y1 && x2 == p.x2 && y2 == p.y2 && x3 == p.x3 && y3 == p.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}