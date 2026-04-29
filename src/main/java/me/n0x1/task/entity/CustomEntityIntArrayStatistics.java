package me.n0x1.task.entity;

import java.util.Objects;

public class CustomEntityIntArrayStatistics {
    private final int min;
    private final int max;
    private final long sum;
    private final double average;

    public CustomEntityIntArrayStatistics(int min, int max, long sum, double average) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomEntityIntArrayStatistics that = (CustomEntityIntArrayStatistics) o;
        return min == that.min && max == that.max && sum == that.sum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, sum);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(min).append(max).append(sum).append(average).toString();
    }

}
