package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.QuantityMeasurementApp.feature.uc3genericquantityclassFordryPrinciple.Length;
import com.QuantityMeasurementApp.feature.uc3genericquantityclassFordryPrinciple.Length.LengthUnit;

public class LengthTest {
	
	@Test
	void testEquality_FeetToFeet_SameValue() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		Length q2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(q1, q2);
	}
	
	@Test
    void testEquality_InchToInch_SameValue() {
		Length q1 = new Length(1.0, LengthUnit.INCHES);
		Length q2 = new Length(1.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }
}
