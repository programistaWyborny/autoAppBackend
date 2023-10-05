package pl.pkowalc.praca.share;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/share")
@RequiredArgsConstructor
class ShareController {

    private final ShareService shareService;

    @PostMapping()
    void shareCar(@RequestBody ShareRequest shareRequest, @RequestAttribute String userName) {
        shareService.shareCar(shareRequest.getCarId(), shareRequest.getSharedWithUsername(), userName);
    }

}
