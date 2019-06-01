package com.jcommerce.cars.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 100, min = 1)
    @Column(nullable = false)
    private String carBrand;

    @Length(max = 100, min = 1)
    @Column(nullable = false)
    private String carModel;

    @Length(max = 100, min = 1)
    @Column(nullable = false)
    private String engine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private int powerHp;

    @Lob
    @Length(max = 1000, min = 5)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfProduction;

    @Lob
    @Column(name = "payload")
    private String payload;

    public Car(String carBrand, String carModel, String engine, FuelType fuelType, int powerHp, String description, Date dateOfProduction, String payload) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.engine = engine;
        this.fuelType = fuelType;
        this.powerHp = powerHp;
        this.description = description;
        this.dateOfProduction = dateOfProduction;
        this.payload = payload;
    }

    public Car(){

    }
}
