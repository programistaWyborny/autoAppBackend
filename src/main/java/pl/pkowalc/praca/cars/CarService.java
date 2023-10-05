package pl.pkowalc.praca.cars;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pkowalc.praca.common.NotFoundException;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarResponse> getCars(String userName) {
        return carRepository.findCars(userName)
                .stream()
                .map(this::getCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse getCarResponse(Integer id, String userName) {
        return carRepository.findCarById(id, userName)
                .map(this::getCarResponse)
                .orElseThrow(NotFoundException::new);
    }

    public CarEntity getUserOwnedCar(Integer id, String userName) {
        return carRepository.findByIdAndUsername(id, userName)
                .orElseThrow(() -> new NotFoundException("Car not found"));
    }

    public CarEntity getUserCar(Integer id, String userName) {
        return carRepository.findCarById(id, userName)
                .orElseThrow(() -> new NotFoundException("Car not found"));
    }

    public void removeCar(Integer id, String userName) {
        carRepository.deleteByIdAndUsername(id, userName);
    }

    public CreateCarResponse addCar(CreateCarRequest request, String userName) {
        final var car = carRepository.save(CarEntity.builder()
                .model(request.getModel())
                .brand(request.getBrand())
                .name(request.getName())
                .year(request.getYear())
                .description(request.getDescription())
                .username(userName)
                .build());
        return new CreateCarResponse(car.getId());
    }

    private CarResponse getCarResponse(CarEntity carEntity) {
        return new CarResponse(carEntity.getId(), carEntity.getName(), carEntity.getBrand(), carEntity.getModel(), carEntity.getYear(), carEntity.getDescription(), carEntity.getUsername());
    }

}
