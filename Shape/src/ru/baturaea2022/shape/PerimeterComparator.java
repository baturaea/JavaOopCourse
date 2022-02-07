package ru.baturaea2022.shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
