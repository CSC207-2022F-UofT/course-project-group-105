package com.mg105.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Comparator;

/**
 * A comparator for calculating distances between Points.
 */
public class PointComparator implements Comparator<Point> {
    private final @NotNull Point reference;

    /**
     * Create a new PointComparator.
     *
     * @param reference the point that distances will be compared against.
     */
    public PointComparator(@NotNull Point reference) {
        this.reference = reference;
    }

    /**
     * Calculate the cartesian distance between the reference and the destination.
     *
     * @param destination the point whose distance is calculated.
     *
     * @return the cartesisan distance between destination and the reference point.
     */
    public int distanceTo(@NotNull Point destination) {
        return Math.abs(destination.x - reference.x) + Math.abs(destination.y - reference.y);
    }

    /**
     * Compare two points.
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     *
     * @return less than 0 if o1 is closer than o2 to the reference point, greater than 0 if o2 is closer than o1,
     *         and 0 otherwise.
     */
    @Override
    public int compare(Point o1, Point o2) {
        return distanceTo(o1) - distanceTo(o2);
    }
}
