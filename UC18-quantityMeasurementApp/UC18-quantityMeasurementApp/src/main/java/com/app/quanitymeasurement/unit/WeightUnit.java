package com.app.quanitymeasurement.unit;

public enum WeightUnit implements IMeasurable {

    KILOGRAMS(1.0),
    GRAMS(0.001),
    POUNDS(0.453592);

    private final double conversion;

    WeightUnit(double conversion) {
        this.conversion = conversion;
    }

    @Override
    public double getConversionFactor() {
        return conversion;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversion;
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return value / conversion;
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

        for (WeightUnit weight : WeightUnit.values()) {

            if (weight.name().equalsIgnoreCase(unitName)) {
                return weight;
            }
        }

        throw new IllegalArgumentException("Unit does not exist: " + unitName);
    }
}