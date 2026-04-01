package com.app.quanitymeasurement.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import java.util.logging.Logger;

import io.swagger.v3.oas.annotations.media.Schema;

import com.app.quanitymeasurement.unit.*;

@Data
@Schema(description = "A quantity with a value and unit")
public class QuantityDTO {

    private static final Logger logger =
            Logger.getLogger(QuantityDTO.class.getName());


    @NotNull(message = "Value cannot be null")
    @Schema(example = "10.0")
    private Double value;

    @NotNull(message = "Unit cannot be null")
    @Schema(
            example = "FEET",
            allowableValues = {
                    "FEET", "INCHES", "YARD", "CENTIMETRE",
                    "LITRE", "MILLILITRE", "GALLON",
                    "GRAM", "KILOGRAM", "POUND",
                    "CELSIUS", "FAHRENHEIT", "KELVIN"
            }
    )
    private String unit;

    @NotNull(message = "Measurement type cannot be null")
    @Pattern(
            regexp = "LengthUnit|VolumeUnit|WeightUnit|Temperature",
            message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, Temperature"
    )
    @Schema(
            example = "LengthUnit",
            allowableValues = {
                    "LengthUnit",
                    "VolumeUnit",
                    "WeightUnit",
                    "Temperature"
            }
    )
    private String measurementType;

    public QuantityDTO() {}

    public QuantityDTO(Double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    @AssertTrue(message = "Unit must be valid for the specified measurement type")
    public boolean isValidUnit() {

        logger.info("Validating unit: " + unit +
                " for measurement type: " + measurementType);

        try {
            switch (measurementType) {

                case "LengthUnit":
                    LengthUnit.valueOf(unit);
                    break;

                case "VolumeUnit":
                    VolumeUnit.valueOf(unit);
                    break;

                case "WeightUnit":
                    WeightUnit.valueOf(unit);
                    break;

                case "Temperature":
                    Temperature.valueOf(unit);
                    break;

                default:
                    return false;
            }

        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }
}