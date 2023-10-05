package pl.pkowalc.praca.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pkowalc.praca.cars.CarService;
import pl.pkowalc.praca.common.NotFoundException;

@Service
@RequiredArgsConstructor
class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CarService carService;

    public List<ServiceResponse> getServices(String userName) {
        return serviceRepository.findByUsername(userName)
                .stream()
                .map(serviceEntity -> new ServiceResponse(serviceEntity.getId(), serviceEntity.getContent(), serviceEntity.getPrice(), serviceEntity.getMilage(), serviceEntity.getCar().getId()))
                .collect(Collectors.toList());
    }

    public List<ServiceResponse> getServices(Integer carId, String userName) {
        return serviceRepository.findByCarIdAndUsername(carId, userName)
                .stream()
                .map(serviceEntity -> new ServiceResponse(serviceEntity.getId(), serviceEntity.getContent(), serviceEntity.getPrice(), serviceEntity.getMilage(), serviceEntity.getCar().getId()))
                .collect(Collectors.toList());
    }

    public ServiceResponse getService(Integer id, String userName) {
        return serviceRepository.findByIdAndUsername(id, userName)
                .map(serviceEntity -> new ServiceResponse(serviceEntity.getId(), serviceEntity.getContent(), serviceEntity.getPrice(), serviceEntity.getMilage(), serviceEntity.getCar().getId()))
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public CreateServiceResponse add(CreateServiceRequest request, String userName) {
        final var car = carService.getUserCar(request.getCarId(), userName);
        final var newService = ServiceEntity.builder()
                .car(car)
                .content(request.getContent())
                .price(request.getPrice())
                .milage(request.getMilage())
                .build();
        final var savedService = serviceRepository.save(newService);
        return new CreateServiceResponse(savedService.getId());
    }

    public void delete(Integer id, String userName) {
        final var service = serviceRepository.findByIdAndUsername(id, userName)
                .orElseThrow(NotFoundException::new);
        serviceRepository.delete(service);
    }
}
