package com.app.quanitymeasurement.controller;

import com.app.quanitymeasurement.model.QuantityDTO;
import com.app.quanitymeasurement.model.QuantityInputDTO;
import com.app.quanitymeasurement.model.QuantityMeasurementDTO;
import com.app.quanitymeasurement.model.OperationType;
import com.app.quanitymeasurement.services.IQuantityMeasurementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantities")
@Tag(name = "Quantity Measurements", description = "REST API for quantity measurement operations")
public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String start(){
        return "Quantity Measurement App is running";
    }

    // ==================== COMPARE ====================
    @PostMapping("/compare")
    @Operation(
            summary = "Compare two quantities",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(examples = {
                            @ExampleObject(name = "Feet = Inches",
                                    value = """
                                    {
                                      "thisQuantityDTO": {"value":1.0,"unit":"FEET","measurementType":"LengthUnit"},
                                      "thatQuantityDTO": {"value":12.0,"unit":"INCHES","measurementType":"LengthUnit"}
                                    }
                                    """)
                    })
            )
    )
    public ResponseEntity<QuantityMeasurementDTO> performComparison(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    // ==================== CONVERT ====================
    @PostMapping("/convert")
    @Operation(summary = "Convert quantity to target unit")
    public ResponseEntity<QuantityMeasurementDTO> performConversion(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.convert(
                        input.getThisQuantityDTO(),
                        input.getTargetQuantityDTO()
                )
        );
    }

    // ==================== ADD ====================
    @PostMapping("/add")
    @Operation(summary = "Add two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performAddition(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    // ==================== ADD WITH TARGET ====================
//    @PostMapping("/add-with-target-unit")
//    @Operation(summary = "Add two quantities with target unit")
//    public ResponseEntity<QuantityMeasurementDTO> performAdditionWithTargetUnit(
//            @Valid @RequestBody QuantityInputDTO input) {
//
//        return ResponseEntity.ok(
//                service.add(
//                        input.getThisQuantityDTO(),
//                        input.getThatQuantityDTO(),
//                        input.getTargetQuantityDTO()
//                )
//        );
//    }

    // ==================== SUBTRACT ====================
    @PostMapping("/subtract")
    @Operation(summary = "Subtract two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performSubtraction(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    // ==================== SUBTRACT WITH TARGET ====================
//    @PostMapping("/subtract-with-target-unit")
//    @Operation(summary = "Subtract two quantities with target unit")
//    public ResponseEntity<QuantityMeasurementDTO> performSubtractionWithTargetUnit(
//            @Valid @RequestBody QuantityInputDTO input) {
//
//        return ResponseEntity.ok(
//                service.subtract(
//                        input.getThisQuantityDTO(),
//                        input.getThatQuantityDTO(),
//                        input.getTargetQuantityDTO()
//                )
//        );
//    }

    // ==================== DIVIDE ====================
    @PostMapping("/divide")
    @Operation(summary = "Divide two quantities")
    public ResponseEntity<QuantityMeasurementDTO> performDivision(
            @Valid @RequestBody QuantityInputDTO input) {

        return ResponseEntity.ok(
                service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO())
        );
    }

    // ==================== HISTORY BY OPERATION ====================
    @GetMapping("/history/operation/{operation}")
    @Operation(summary = "Get operation history")
    public ResponseEntity<List<QuantityMeasurementDTO>> getOperationHistory(
            @PathVariable OperationType operation) {

        return ResponseEntity.ok(service.getOperationHistory(operation.getDisplayName()));
    }

    // ==================== COUNT ====================
//    @GetMapping("/count/{operation}")
//    @Operation(summary = "Get operation count")
//    public ResponseEntity<Long> getOperationCount(
//            @PathVariable OperationType operation) {
//
//        return ResponseEntity.ok(service.getOperationCount(operation.getDisplayName()));
//    }
//
//    // ==================== ERROR HISTORY ====================
//    @GetMapping("/history/errored")
//    @Operation(summary = "Get errored operations history")
//    public ResponseEntity<List<QuantityMeasurementDTO>> getErroredOperations() {
//
//        return ResponseEntity.ok(service.getErrorHistory());
//    }
}