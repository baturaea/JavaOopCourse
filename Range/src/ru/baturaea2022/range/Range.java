package ru.baturaea2022.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (isInside(range.from) || isInside(range.to)) {
            return new Range(Math.max(range.from, from), Math.min(range.to, to));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (isInside(range.from) || isInside(range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }

        return new Range[]{
                new Range(from, to),
                new Range(range.from, range.to)
        };
    }

    public Range[] getDifference(Range range) {
        if (from >= range.to || to <= range.from) {
            return new Range[]{new Range(from, to)};
        }

        if (from >= range.from && to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        if (from < range.from && to <= range.to ) {
            return new Range[]{new Range(from, range.from)};
        }

        if (from < range.from) {
            return new Range[]{
                    new Range(from, range.from),
                    new Range(range.to, to)
            };
        }

        return new Range[]{};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}