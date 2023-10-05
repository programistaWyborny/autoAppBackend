package pl.pkowalc.praca.share;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pkowalc.praca.cars.CarService;
import pl.pkowalc.praca.common.NotFoundException;
import pl.pkowalc.praca.user.UserService;

@Service
@RequiredArgsConstructor
class ShareService {

    private final CarService carService;
    private final UserService userService;

    @Transactional
    public void shareCar(Integer carId, String sharedWithUsername, String ownerUserName) {
        if(sharedWithUsername == ownerUserName){
            throw new NotFoundException("Invalid username (your own username received");
        }
        else {
            final var sharedCar = carService.getUserOwnedCar(carId, ownerUserName);
            final var user = userService.getUser(sharedWithUsername);
            sharedCar.getAllowedUsers().add(user);
        }
    }
}
