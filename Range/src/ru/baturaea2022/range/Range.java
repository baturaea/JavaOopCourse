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

    public boolean isInside(double checkNumber) {
        return checkNumber > from && checkNumber < to;
    }

    public Range getIntersection(Range range) {
        if (this.getFrom() <= range.getFrom() && this.getTo() >= range.getFrom()
                || this.getTo() >= range.getTo() && this.getTo() <= range.getTo()) {
            return new Range(Math.max(range.getFrom(), this.getFrom()), Math.min(range.getTo(), this.getTo()));
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (this.isInside(range.getFrom()) || this.isInside(range.getTo())) {
            return new Range[]{new Range(Math.min(this.getFrom(), range.getFrom()), Math.max(this.getTo(), range.getTo()))};
        }

        return new Range[]{this, range};
    }

    public Range[] getDifference(Range range) {
        if (this.getFrom() == range.getFrom() && this.getTo() == range.getTo()) {
            return new Range[]{new Range(0, 0)};
        } else if (this.isInside(range.getFrom()) && this.isInside(range.getTo())) {
            return new Range[]{
                    new Range(this.getFrom(), range.getFrom()),
                    new Range(range.getTo(), this.getTo())
            };
        } else if (this.isInside(range.getFrom())) {
            return new Range[]{new Range(this.getFrom(), range.getFrom())};
        } else if (this.isInside(range.getTo())) {
            return new Range[]{new Range(range.getTo(), this.getTo())};
        }

        return new Range[]{this};
    }
}