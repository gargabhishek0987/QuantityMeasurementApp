package quantity;

public enum VolumeUnit implements IMeasurable{
    LITRE(1.0), 
    MILLILITRE(0.001), 
    GALLON(3.78541); 

    private final double conversionValue;

    VolumeUnit(double conversionValue){
        this.conversionValue = conversionValue;
    }

    @Override
    public double getConversionValue() {return conversionValue;}

    @Override
    public double convertToBaseUnit(double value) {return value*conversionValue;}

    @Override
    public double convertFromBaseUnit(double baseValue) {return baseValue/conversionValue;}

    @Override
    public String getUnitName() {return this.name();}
}
