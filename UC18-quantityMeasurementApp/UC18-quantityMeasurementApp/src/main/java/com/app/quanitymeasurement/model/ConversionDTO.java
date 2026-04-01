package com.app.quanitymeasurement.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConversionDTO {

    @Valid
    @NotNull(message = "This quantity must not be null")
    private QuantityDTO thisQuantityDTO;

    @Valid
    private QuantityDTO targetQuantityDTO;
}
