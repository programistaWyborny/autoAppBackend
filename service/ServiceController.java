package pl.pkowalc.praca.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pkowalc.praca.service.CreateServiceRequest;
import pl.pkowalc.praca.service.CreateServiceResponse;
import pl.pkowalc.praca.service.ServiceResponse;
import pl.pkowalc.praca.service.ServiceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
class ServiceController {

    private final ServiceService serviceService;

    @GetMapping()
    List<ServiceResponse> getServices(@RequestAttribute String userName) {
        return serviceService.getServices(userName);
    }

    @GetMapping(params = "carId")
    List<ServiceResponse> getServices(@RequestParam Integer carId, @RequestAttribute String userName) {
        return serviceService.getServices(carId, userName);
    }

    @GetMapping("/{id}")
    ServiceResponse getService(@PathVariable Integer id, @RequestAttribute String userName) {
        return serviceService.getService(id, userName);
    }

    @PostMapping("")
    CreateServiceResponse create(@RequestBody @Valid CreateServiceRequest request, @RequestAttribute String userName) {
        return serviceService.add(request, userName);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id, @RequestAttribute String userName) {
        serviceService.delete(id, userName);
    }


}
