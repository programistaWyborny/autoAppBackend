package pl.pkowalc.praca.cars;

import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class CarsController {

    private final CarService carService;

    @GetMapping("/cars")
    List<CarResponse> getCars(@RequestAttribute String userName) {
        return carService.getCars(userName);
    }

    @GetMapping("/cars/{carId}")
    CarResponse getCar(@PathVariable Integer carId, @RequestAttribute String userName) {
        return carService.getCarResponse(carId, userName);
    }

    @DeleteMapping("/cars/{carId}")
    void deleteCar(@PathVariable Integer carId, @RequestAttribute String userName) {
        carService.removeCar(carId, userName);
    }

    @PostMapping("/cars")
    CreateCarResponse createCar(@RequestBody @Valid CreateCarRequest request, @RequestAttribute String userName) {
        return carService.addCar(request, userName);
    }

}
