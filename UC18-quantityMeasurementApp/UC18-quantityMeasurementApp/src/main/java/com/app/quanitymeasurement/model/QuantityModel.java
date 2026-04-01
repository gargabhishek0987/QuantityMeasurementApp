package com.app.quanitymeasurement.model;

import com.app.quanitymeasurement.unit.IMeasurable;

public class QuantityModel<T extends IMeasurable> {

    private final double value;
    private final T unit;

    public QuantityModel(double value, T unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public T getUnit() {
        return unit;
    }


    public double convertTo(IMeasurable targetUnit) {

        double baseValue = unit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof QuantityModel)) return false;

        QuantityModel<?> other = (QuantityModel<?>) obj;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    public double add(QuantityModel<?> other, IMeasurable targetUnit) {

        double v1 = this.convertTo(targetUnit);
        double v2 = other.convertTo(targetUnit);

        return v1 + v2;
    }

    public double subtract(QuantityModel<?> other, IMeasurable targetUnit) {

        double v1 = this.convertTo(targetUnit);
        double v2 = other.convertTo(targetUnit);

        return v1 - v2;
    }

    public double divide(QuantityModel<?> other) {

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return base1 / base2;
    }
}
