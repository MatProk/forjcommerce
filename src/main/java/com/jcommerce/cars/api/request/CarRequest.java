package com.jcommerce.cars.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jcommerce.cars.domain.FuelType;
import lombok.Data;

import java.util.Date;

@Data
public class CarRequest {
    private String carBrand;
    private String carModel;
    private String engine;
    private FuelType fuelType;
    private int powerHp;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfProduction;
    private String payload;

    public CarRequest(String carBrand, String carModel, String engine, FuelType fuelType, int powerHp, String description, Date dateOfProduction, String payload) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.engine = engine;
        this.fuelType = fuelType;
        this.powerHp = powerHp;
        this.description = description;
        this.dateOfProduction = dateOfProduction;
        this.payload = payload;
    }
}