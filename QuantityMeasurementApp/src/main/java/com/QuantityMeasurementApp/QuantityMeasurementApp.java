package com.QuantityMeasurementApp;

public class QuantityMeasurementApp {
	static class Feet {
	    private final double value;

	    public Feet(double value) {
	        this.value = value;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Feet other = (Feet) obj;
	        return Double.compare(this.value, other.value) == 0;
	    }

	    @Override
	    public int hashCode() {
	        return Double.hashCode(value);
	    }
	}
	
	static class Inches{
		private final double value;
		public Inches(double value) {
			this.value = value;
		}
		
		@Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Inches other = (Inches) obj;
	        return Double.compare(this.value, other.value) == 0;
	    }

	    @Override
	    public int hashCode() {
	        return Double.hashCode(value);
	    }
	}
	
	public static boolean compareFeet(double a, double b) {
        return new Feet(a).equals(new Feet(b));
    }

    public static boolean compareInches(double a, double b) {
        return new Inches(a).equals(new Inches(b));
    }
	
	public static void main(String[] args) {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(2.0);
		Inches i1 = new Inches(1.0);
		Inches i2 = new Inches(2.0);
		System.out.println(i1.equals(i2));
	}
}
