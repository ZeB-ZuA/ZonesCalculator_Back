package com.udistrital.zones_calculator.emuns;

public enum DensityZone {

    COMPLIANCE(0.0, 10.0), // Compliance zone: density from 0 to 10
    OCCUPATIONAL(10.1, 50.0), // Occupational zone: density from 10.1 to 50
    EXCEEDANCE(50.1, Double.MAX_VALUE); // Exceedance zone: density from 50.1 to Double.MAX_VALUE

    private final double minDensity;
    private final double maxDensity;

    DensityZone(double minDensity, double maxDensity) {
        this.minDensity = minDensity;
        this.maxDensity = maxDensity;
    }

    public double getMinDensity() {
        return minDensity;
    }

    public double getMaxDensity() {
        return maxDensity;
    }

    public static DensityZone fromDensity(double density) {
        for (DensityZone zone : values()) {
            if (density >= zone.getMinDensity() && density <= zone.getMaxDensity()) {
                return zone;
            }
        }
        throw new IllegalArgumentException("Densidad fuera de rango: " + density);
    }

}
