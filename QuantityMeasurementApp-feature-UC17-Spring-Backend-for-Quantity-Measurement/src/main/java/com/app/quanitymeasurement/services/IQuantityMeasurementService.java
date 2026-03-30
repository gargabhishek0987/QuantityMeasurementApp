package com.app.quanitymeasurement.services;

import com.app.quanitymeasurement.model.QuantityDTO;
import com.app.quanitymeasurement.model.QuantityMeasurementDTO;
import com.app.quanitymeasurement.unit.IMeasurable;

import java.util.List;
public interface IQuantityMeasurementService {

    
    public QuantityMeasurementDTO compare(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    );

    
    public QuantityMeasurementDTO convert(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    );

    
    public QuantityMeasurementDTO add(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    );

    
//    public QuantityMeasurementDTO add(
//            QuantityDTO thisQuantityDTO,
//            QuantityDTO thatQuantityDTO,
//            QuantityDTO targetUnit
//    );

    
    public QuantityMeasurementDTO subtract(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    );


//    public QuantityMeasurementDTO subtract(
//            QuantityDTO thisQuantityDTO,
//            QuantityDTO thatQuantityDTO,
//            QuantityDTO targetUnit
//    );

    
    public QuantityMeasurementDTO divide(
            QuantityDTO thisQuantityDTO,
            QuantityDTO thatQuantityDTO
    );

    
    List<QuantityMeasurementDTO> getOperationHistory(String operation);

}