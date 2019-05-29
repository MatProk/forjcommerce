package com.jcommerce.cars.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CarRequest {
    private String carBrand;
    private String carModel;
    private String description;
    private int power;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfProduction;
    private String payload;

    public CarRequest(String carBrand, String carModel, String description, int power, Date dateOfProduction, String payload) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.description = description;
        this.power = power;
        this.dateOfProduction = dateOfProduction;
        this.payload = payload;
    }

}
