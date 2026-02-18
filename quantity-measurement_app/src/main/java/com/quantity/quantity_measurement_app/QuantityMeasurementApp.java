package com.quantity.quantity_measurement_app;

import java.util.Objects;

public class QuantityMeasurementApp {
    public static class Feet{
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue(){
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Feet feet = (Feet) o;
            return Double.compare(value, feet.value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }

    public static void main(String args[]){
        Feet one = new Feet(1.0);
        Feet two = new Feet(2.0);
        System.out.println(one.equals(two));
    }
}

