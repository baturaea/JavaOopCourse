package ru.baturaea2022.shape;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private final double[] sideArray;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.sideArray = new double[]{getSideLength(x1, y1, x2, y2), getSideLength(x2, y2, x3, y3), getSideLength(x1, y1, x3, y3)};
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
        sideArray[0] = getSideLength(x1, y1, x2, y2);
        sideArray[2] = getSideLength(x1, y1, x3, y3);
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
        sideArray[0] = getSideLength(x1, y1, x2, y2);
        sideArray[2] = getSideLength(x1, y1, x3, y3);
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
        sideArray[0] = getSideLength(x1, y1, x2, y2);
        sideArray[1] = getSideLength(x2, y2, x3, y3);
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
        sideArray[0] = getSideLength(x1, y1, x2, y2);
        sideArray[1] = getSideLength(x2, y2, x3, y3);
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
        sideArray[2] = getSideLength(x1, y1, x3, y3);
        sideArray[1] = getSideLength(x2, y2, x3, y3);
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
        sideArray[2] = getSideLength(x1, y1, x3, y3);
        sideArray[1] = getSideLength(x2, y2, x3, y3);
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getPerimeter() {
        return sideArray[0] + sideArray[1] + sideArray[2];
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - sideArray[0]) *
                (semiPerimeter - sideArray[1]) * (semiPerimeter - sideArray[2]));
    }

    @Override
    public String toString() {
        return "Треугольник: {" + x1 + ", " + y1 + "}, {" + x2 + ", " + y2 + "}, {" + x3 + ", " + y3
                + "}; Площадь = " + getArea() + "; Периметр = " + getPerimeter()  + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2 && y2 == triangle.y2
                && x3 == triangle.x3 && y3 == triangle.y3;
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