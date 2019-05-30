package com.jcommerce.cars.service.car;

import com.jcommerce.cars.api.request.CarRequest;
import com.jcommerce.cars.domain.Car;
import com.jcommerce.cars.exception.ResourceNotFoundException;
import com.jcommerce.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public void saveCar(CarRequest carRequest) {
        carRepository.save(new Car(carRequest.getCarBrand(), carRequest.getCarModel(), carRequest.getEngine(), carRequest.getFuelType(), carRequest.getPowerHp(), carRequest.getDescription(), carRequest.getDateOfProduction(), carRequest.getPayload()));
    }

    @Override
    public Optional<Car> getOneCar(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateCar(Long id, CarRequest carRequest) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "id", id));

        car.setCarBrand(carRequest.getCarBrand());
        car.setCarModel(carRequest.getCarModel());
        car.setEngine(carRequest.getEngine());
        car.setFuelType(carRequest.getFuelType());
        car.setPowerHp(carRequest.getPowerHp());
        car.setDateOfProduction(carRequest.getDateOfProduction());
        car.setPayload(carRequest.getPayload());

        carRepository.save(car);
    }
}
