package pl.pkowalc.praca.fueling;

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
@RequestMapping("/api/fuelings")
@RequiredArgsConstructor
class FuelingController {

    private final FuelingService fuelingService;

    @GetMapping()
    List<FuelingResponse> getFuelings(@RequestAttribute String userName) {
        return fuelingService.getFuelings(userName);
    }

    @GetMapping("/{id}")
    FuelingResponse getFueling(@PathVariable Integer id, @RequestAttribute String userName) {
        return fuelingService.getFueling(id, userName);
    }

    @PostMapping("")
    CreateFuelingResponse create(@RequestBody @Valid CreateFuelingRequest request, @RequestAttribute String userName) {
        return fuelingService.add(request, userName);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id, @RequestAttribute String userName) {
        fuelingService.delete(id, userName);
    }
    
}
