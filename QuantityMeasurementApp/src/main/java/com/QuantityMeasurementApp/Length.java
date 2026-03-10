package com.QuantityMeasurementApp.feature.uc3genericquantityclassFordryPrinciple;

import java.util.Objects;

public class Length {
	
	public enum LengthUnit{
		FEET(12.0),INCHES(1.0);
		
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
	
	public double convertToBaseUnit() {
		double conversionFactor = unit.conversionFactor;
		return value * conversionFactor;
	}
	
	public boolean compare(Length thatLength) {
		return this.convertToBaseUnit()==thatLength.convertToBaseUnit();
	}

	@Override
	public int hashCode() {
		return Objects.hash(unit, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Length other = (Length) obj;
		return compare(other);
	}
	
	public static void main(String[] args) {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		System.out.println("both lengths are: "+l1.equals(l2));
	}
}