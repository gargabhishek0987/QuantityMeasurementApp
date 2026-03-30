package com.app.quanitymeasurement.model;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class QuantityMeasurementDTO {

    public double thisValue;
    public String thisUnit;
    public String thisMeasurementType;

    public double thatValue;
    public String thatUnit;
    public String thatMeasurementType;

    public String operation;

    public String resultString;
    public double resultValue;
    public String resultUnit;
    public String resultMeasurementType;

    public String errorMessage;

    // ✅ FIX: Proper boolean mapping with Jackson
    @JsonProperty("error")
    public boolean error;

    /**
     * Convert Entity to DTO
     */
    public static QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {

        if (entity == null) {
            return null;
        }

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.thisValue = entity.getThisValue();
        dto.thisUnit = entity.getThisUnit();
        dto.thisMeasurementType = entity.getThisMeasurementType();

        dto.thatValue = entity.getThatValue();
        dto.thatUnit = entity.getThatUnit();
        dto.thatMeasurementType = entity.getThatMeasurementType();

        dto.operation = entity.getOperation();

        dto.resultString = entity.getResultString();
        dto.resultValue = entity.getResultValue();
        dto.resultUnit = entity.getResultUnit();
        dto.resultMeasurementType = entity.getResultMeasurementType();

        dto.error = entity.isError();
        dto.errorMessage = entity.getErrorMessage();

        return dto;
    }

    /**
     * Convert DTO to Entity
     */
    public QuantityMeasurementEntity toEntity() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        entity.setThisValue(thisValue);
        entity.setThisUnit(thisUnit);
        entity.setThisMeasurementType(thisMeasurementType);

        entity.setThatValue(thatValue);
        entity.setThatUnit(thatUnit);
        entity.setThatMeasurementType(thatMeasurementType);

        entity.setOperation(operation);

        entity.setResultString(resultString);
        entity.setResultValue(resultValue);
        entity.setResultUnit(resultUnit);
        entity.setResultMeasurementType(resultMeasurementType);

        entity.setError(error);
        entity.setErrorMessage(errorMessage);

        return entity;
    }

    /**
     * Convert List<Entity> → List<DTO>
     */
    public static List<QuantityMeasurementDTO> fromList(List<QuantityMeasurementEntity> entities) {
        return entities.stream()
                .map(QuantityMeasurementDTO::from)
                .collect(Collectors.toList());
    }

    /**
     * Convert List<DTO> → List<Entity>
     */
    public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
        return dtos.stream()
                .map(QuantityMeasurementDTO::toEntity)
                .collect(Collectors.toList());
    }
}