package com.app.quanitymeasurement.services;

import com.app.quanitymeasurement.model.*;
import com.app.quanitymeasurement.repository.QuantityMeasurementRepository;
import com.app.quanitymeasurement.unit.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final QuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // ====================== COMPARE ======================
    @Override
    public QuantityMeasurementDTO compare(QuantityDTO q1, QuantityDTO q2) {
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            QuantityModel<IMeasurable> m2 = convertDtoToModel(q2);

            boolean result = m1.equals(m2);

            return saveResult(q1, q2, result ? 1 : 0, OperationType.COMPARE, false, null);

        } catch (Exception e) {
            return saveResult(q1, q2, 0, OperationType.COMPARE, true, e.getMessage());
        }
    }

    // ====================== CONVERT ======================
    @Override
    public QuantityMeasurementDTO convert(QuantityDTO source, QuantityDTO target) {
        try {
            QuantityModel<IMeasurable> model = convertDtoToModel(source);
            IMeasurable targetUnit = getUnit(target);

            double result = model.convertTo(targetUnit);

            return saveResult(source, target, result, OperationType.CONVERT, false, null);

        } catch (Exception e) {
            return saveResult(source, target, 0, OperationType.CONVERT, true, e.getMessage());
        }
    }

    // ====================== ADD ======================
    @Override
    public QuantityMeasurementDTO add(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, q1, OperationType.ADD, Double::sum);
    }

    // ====================== SUBTRACT ======================
    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return performArithmetic(q1, q2, q1, OperationType.SUBTRACT, (a, b) -> a - b);
    }

    // ====================== DIVIDE ======================
    @Override
    public QuantityMeasurementDTO divide(QuantityDTO q1, QuantityDTO q2) {
        if (q2.getValue() == 0) {
            return saveResult(q1, q2, 0, OperationType.DIVIDE, true, "Divide by zero");
        }
        return performArithmetic(q1, q2, q1, OperationType.DIVIDE, (a, b) -> a / b);
    }

    // ====================== HISTORY ======================
    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        return repository.findByOperation(operation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ====================== COMMON ======================

    private QuantityMeasurementDTO performArithmetic(
            QuantityDTO q1,
            QuantityDTO q2,
            QuantityDTO target,
            OperationType operation,
            DoubleBinaryOperator operator
    ) {
        try {
            QuantityModel<IMeasurable> m1 = convertDtoToModel(q1);
            QuantityModel<IMeasurable> m2 = convertDtoToModel(q2);
            IMeasurable targetUnit = getUnit(target);

            double result = operator.applyAsDouble(
                    m1.convertTo(targetUnit),
                    m2.convertTo(targetUnit)
            );

            return saveResult(q1, q2, result, targetUnit, operation, false, null);

        } catch (Exception e) {
            return saveResult(q1, q2,0, null, operation, true, e.getMessage());
        }
    }

    private QuantityModel<IMeasurable> convertDtoToModel(QuantityDTO dto) {
        return new QuantityModel<>(dto.getValue(), getUnit(dto));
    }

    // 🔥 FIXED (no null return)
    private IMeasurable getUnit(QuantityDTO dto) {
        switch (dto.getMeasurementType()) {
            case "LengthUnit":
                return LengthUnit.valueOf(dto.getUnit());
            case "WeightUnit":
                return WeightUnit.valueOf(dto.getUnit());
            case "VolumeUnit":
                return VolumeUnit.valueOf(dto.getUnit());
            case "TemperatureUnit":
                return Temperature.valueOf(dto.getUnit());
            default:
                throw new IllegalArgumentException("Invalid measurement type: " + dto.getMeasurementType());
        }
    }

    // 🔥 FIXED SAVE (FULL DATA STORED)
    private QuantityMeasurementDTO saveResult(
            QuantityDTO q1,
            QuantityDTO q2,
            double result,
            IMeasurable resultUnit,
            OperationType operation,
            boolean isError,
            String errorMsg
    ) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit());
        entity.setThisMeasurementType(q1.getMeasurementType());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnit());
        entity.setThatMeasurementType(q2.getMeasurementType());

        entity.setOperation(operation.name());
        entity.setResultValue(result);
        entity.setResultUnit(String.valueOf(resultUnit));
        entity.setResultMeasurementType(q1.getMeasurementType());
        entity.setError(isError);
        entity.setErrorMessage(errorMsg);

        repository.save(entity);

        return mapToDTO(entity);
    }

    private QuantityMeasurementDTO saveResult(
            QuantityDTO q1,
            QuantityDTO q2,
            double result,
            OperationType operation,
            boolean isError,
            String errorMsg
    ) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(q1.getValue());
        entity.setThisUnit(q1.getUnit());
        entity.setThisMeasurementType(q1.getMeasurementType());

        entity.setThatValue(q2.getValue());
        entity.setThatUnit(q2.getUnit());
        entity.setThatMeasurementType(q2.getMeasurementType());

        entity.setOperation(operation.name());
        entity.setResultValue(result);
        entity.setResultMeasurementType(q1.getMeasurementType());
        entity.setError(isError);
        entity.setErrorMessage(errorMsg);

        repository.save(entity);

        return mapToDTO(entity);
    }

    private QuantityMeasurementDTO mapToDTO(QuantityMeasurementEntity entity) {
        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(entity.getThisValue());
        dto.setThisUnit(entity.getThisUnit());
        dto.setThisMeasurementType(entity.getThisMeasurementType());

        dto.setThatValue(entity.getThatValue());
        dto.setThatUnit(entity.getThatUnit());
        dto.setThatMeasurementType(entity.getThatMeasurementType());

        dto.setOperation(entity.getOperation());
        dto.setResultValue(entity.getResultValue());

        dto.setError(entity.isError());
        dto.setErrorMessage(entity.getErrorMessage());

        return dto;
    }
}