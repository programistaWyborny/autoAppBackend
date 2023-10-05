package pl.pkowalc.praca.service;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
public class CreateServiceRequest {
    
    @NotNull
    String content;

    @NotNull
    Float price;
    
    @NotNull
    Float milage;

    @NotNull
    Integer carId;
    
}
