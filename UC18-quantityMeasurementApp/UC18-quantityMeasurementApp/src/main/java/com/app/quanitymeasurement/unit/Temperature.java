package com.app.quanitymeasurement.unit;

import java.util.function.Function;

public enum Temperature implements IMeasurable {

    CELSIUS(
            c -> c,
            c -> c
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,
            c -> (c * 9 / 5) + 32
    ),

    KELVIN(
            k -> k - 273.15,
            c -> c + 273.15
    );

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    private final SupportArithemetic supportArithemetic = () -> false;

    Temperature(Function<Double, Double> toBase, Function<Double, Double> fromBase) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double value) {
        return fromBase.apply(value);
    }

    @Override
    public boolean supportsArithemetics() {
        return supportArithemetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (!supportArithemetic.isSupported()) {
            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operations."
            );
        }
    }

    public double convertTo(double value, Temperature targetUnit) {
        if (this == targetUnit) {
            return value;
        }

        double baseValue = this.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
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
        for (Temperature temp : Temperature.values()) {
            if (temp.name().equalsIgnoreCase(unitName)) {
                return temp;
            }
        }
        throw new IllegalArgumentException("Unit does not exist: " + unitName);
    }
}