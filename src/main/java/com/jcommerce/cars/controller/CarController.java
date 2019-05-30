package com.jcommerce.cars.controller;

import com.jcommerce.cars.api.request.CarRequest;
import com.jcommerce.cars.domain.Car;
import com.jcommerce.cars.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("cars")
    public List<Car> getAllCars(){
        return carService.findAll();
    }
    @PostMapping("/cars")
    public void saveCar(@RequestBody CarRequest carRequest){
        carService.saveCar(carRequest);
    }

    @GetMapping("cars/{id}")
    public Optional<Car> getOneCar(@PathVariable Long id){
        return carService.getOneCar(id);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @PutMapping("cars/{id}")
    public void updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest){
        carService.updateCar(id, carRequest);
    }
}
