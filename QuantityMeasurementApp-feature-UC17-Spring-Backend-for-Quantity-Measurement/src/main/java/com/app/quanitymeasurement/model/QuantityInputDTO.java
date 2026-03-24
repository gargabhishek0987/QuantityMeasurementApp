package com.app.quanitymeasurement.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuantityInputDTO {

    @Valid
    @NotNull(message = "This quantity must not be null")
    private QuantityDTO thisQuantityDTO;

    @Valid
    @NotNull(message = "That quantity must not be null")
    private QuantityDTO thatQuantityDTO;

    // Optional → used in conversion / target-based operations
    @Valid
    private QuantityDTO targetQuantityDTO;
}