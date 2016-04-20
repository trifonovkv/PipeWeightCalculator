package com.trifonov.kos.pipeweightcalculator;

import java.io.Serializable;

/**
 * The calculation of the weight of the package pipes
 * Created by kos on 01.04.16.
 */
public class PackagePipes implements Serializable{
    static final int STEEL_WEIGHT_KG_M3 = 7850;

    private double diameter;
    private double wallThick;
    private double lengthMin;
    private double lengthMax;
    private int count;

    public PackagePipes() {
    }

    public PackagePipes(double diameter, double wallThick, double lengthMin,
                        double lengthMax, int count) {
        this.diameter = diameter;
        this.wallThick = wallThick;
        this.lengthMin = lengthMin;
        this.lengthMax = lengthMax;
        this.count = count;
    }

    public void setDiameter(double d) {
        diameter = d;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setWallThick(double t) {
        wallThick = t;
    }

    public double getWallThick() {
        return wallThick;
    }

    public void setLengthMin(double l) {
        lengthMin = l;
    }

    public double getLengthMin() {
        return lengthMin;
    }

    public void setLengthMax(double l) {
        lengthMax = l;
    }

    public double getLengthMax() {
        return lengthMax;
    }

    public void setCount(int c) {
        count = c;
    }

    public int getCount() {
        return count;
    }

    public double getWeight() {
        if((diameter * wallThick * lengthMin * lengthMax) == 0)
            return 0;
        double ro = diameter / 2 / 1000;
        double ri = ro - wallThick / 1000;
        double ao = Math.pow(ro, 2) * Math.PI;
        double ai = Math.pow(ri, 2) * Math.PI;
        double length = (lengthMin + lengthMax) / 2;
        return (ao - ai) * length * STEEL_WEIGHT_KG_M3 * count;
    }
}
