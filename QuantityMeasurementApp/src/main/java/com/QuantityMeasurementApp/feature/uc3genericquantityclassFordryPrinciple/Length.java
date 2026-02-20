package com.QuantityMeasurementApp.feature.uc3genericquantityclassFordryPrinciple;

import java.util.Objects;

public class Length {
	
	public enum LengthUnit{
		FEET(12.0),
		INCHES(1.0),
		YARDS(36.0),
		CENTIMETERS(0.393701);
		
		private final double conversionFactor;
		
		LengthUnit(double conversionFactor){
			this.conversionFactor = conversionFactor;
		}
		
		public double getConversionFactor() {
			return conversionFactor;
		}
	}
	
	private double value;
	private LengthUnit unit;
	
	public Length(double value, LengthUnit unit) {
		this.value = value;
		this.unit = unit;
	}
	
	private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    public boolean compare(Length other) {
        if (other == null) return false;

        double a = this.toBaseUnit();
        double b = other.toBaseUnit();

        // handle floating precision
        return Math.abs(a - b) < 0.0001;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;
        Length other = (Length) obj;
        return compare(other);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }
	
    public static void main(String[] args) {

        System.out.println(new Length(1, LengthUnit.YARDS)
                .equals(new Length(3, LengthUnit.FEET)));   // true

        System.out.println(new Length(1, LengthUnit.YARDS)
                .equals(new Length(36, LengthUnit.INCHES))); // true

        System.out.println(new Length(1, LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, LengthUnit.INCHES))); // true

        System.out.println(new Length(2, LengthUnit.YARDS)
                .equals(new Length(72, LengthUnit.INCHES))); // true
    }
}
