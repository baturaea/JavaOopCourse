package ru.baturaea2022.shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidthLength() {
        return width;
    }

    public void setWidthLength(double width) {
        this.width = width;
    }

    public double getHeightLength() {
        return height;
    }

    public void setHeightLength(double height) {
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Прямоугольник(Площадь = " + getArea()
                + "; Ширина = " + getWidth() + "; Высота = " + getHeight() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Rectangle shape = (Rectangle) o;

        return width == shape.width && height == shape.height;
    }

    @Override
    public int hashCode() {
        final int prime = 19;
        int hash = 1;

        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);

        return hash;
    }
}