package com.app.quanitymeasurement.unit;

public enum VolumeUnit implements IMeasurable {

    LITER(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private double conversion;

    VolumeUnit(double conversion) {
        this.conversion = conversion;
    }

    @Override
    public double getConversionFactor() {
        return conversion;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * this.getConversionFactor();
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return value / this.getConversionFactor();
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public String getMeasurementType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (VolumeUnit vol : VolumeUnit.values()) {
            if (vol.name().equalsIgnoreCase(unitName)) {
                return vol;
            }
        }
        throw new IllegalArgumentException("Invalid unit: " + unitName);
    }
}