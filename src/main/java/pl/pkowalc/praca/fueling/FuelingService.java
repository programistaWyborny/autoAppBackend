package pl.pkowalc.praca.fueling;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pkowalc.praca.cars.CarService;
import pl.pkowalc.praca.common.NotFoundException;

@Service
@RequiredArgsConstructor
class FuelingService {

    private final FuelingRepository fuelingRepository;
    private final CarService carService;

    public List<FuelingResponse> getFuelings(String userName) {
        return fuelingRepository.findByUsername(userName)
                .stream()
                .map(fuelingEntity -> new FuelingResponse(fuelingEntity.getId(), fuelingEntity.getMilage(), fuelingEntity.getVolume(),
                        fuelingEntity.getPrice(), fuelingEntity.getCar().getId()))
                .collect(Collectors.toList());
    }

    public FuelingResponse getFueling(Integer id, String userName) {
        return fuelingRepository.findByIdAndUsername(id, userName)
                .map(fuelingEntity -> new FuelingResponse(fuelingEntity.getId(), fuelingEntity.getMilage(), fuelingEntity.getVolume(),
                        fuelingEntity.getPrice(), fuelingEntity.getCar().getId()))
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public CreateFuelingResponse add(CreateFuelingRequest request, String userName) {
        final var car = carService.getUserCar(request.getCarId(), userName);
        final var fueling = FuelingEntity.builder()
                .car(car)
                .milage(request.getState())
                .volume(request.getVolume())
                .price(request.getPrice())
                .build();
        final var fuelingEntity = fuelingRepository.save(fueling);
        return new CreateFuelingResponse(fuelingEntity.getId());
    }

    public void delete(Integer id, String userName) {
        final var entity = fuelingRepository.findByIdAndUsername(id, userName)
                .orElseThrow(NotFoundException::new);
        fuelingRepository.delete(entity);
    }
}
