package com.app.quanitymeasurement.controller;

import com.app.quanitymeasurement.model.*;
import com.app.quanitymeasurement.services.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/quantities")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> performComparison(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    @PostMapping("/conversion")
    @Operation(summary = "Convert quantity to target unit")
    public ResponseEntity<QuantityMeasurementDTO> performConversion(
            @Valid @RequestBody ConversionDTO input) {

        return ResponseEntity.ok(
                service.convert(
                        input.getThisQuantityDTO(),
                        input.getTargetQuantityDTO()
                )
        );
    }

    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performAddition(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO(), input.getTargetQuantityDTO())
        );
    }


    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performSubtraction(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performDivision(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get operation history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(
            @PathVariable OperationType operation) {

        return ResponseEntity.ok(service.getOperationHistory(operation.getDisplayName()));
    }


}