package com.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
	
	@Test
    void testEquality_FeetToInch_EquivalentValue() {
		Length q1 = new Length(1.0, LengthUnit.FEET);
		Length q2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }
	
	@Test
    void testEquality_InchToFeet_EquivalentValue() {
		Length q1 = new Length(12.0, LengthUnit.INCHES);
		Length q2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(q1, q2);
    }
	
	@Test
	void testEquality_YardToFeet() {
	    assertTrue(new Length(1, LengthUnit.YARDS)
	            .equals(new Length(3, LengthUnit.FEET)));
	}

	@Test
	void testEquality_YardToInches() {
	    assertTrue(new Length(1, LengthUnit.YARDS)
	            .equals(new Length(36, LengthUnit.INCHES)));
	}

	@Test
	void testEquality_cmToInches() {
	    assertTrue(new Length(1, LengthUnit.CENTIMETERS)
	            .equals(new Length(0.393701, LengthUnit.INCHES)));
	}

	@Test
	void testInequality_cmToFeet() {
	    assertFalse(new Length(1, LengthUnit.CENTIMETERS)
	            .equals(new Length(1, LengthUnit.FEET)));
	}
}
