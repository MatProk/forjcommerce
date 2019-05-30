package com.jcommerce.cars.service.car;

import com.jcommerce.cars.api.request.CarRequest;
import com.jcommerce.cars.domain.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService {
    List<Car> findAll();
    void saveCar(CarRequest carRequest);
    Optional<Car> getOneCar(Long id);
    void deleteCar(Long id);
    void updateCar(Long id, CarRequest carRequest);
}
