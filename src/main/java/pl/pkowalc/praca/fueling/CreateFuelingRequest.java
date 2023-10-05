package pl.pkowalc.praca.fueling;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
class CreateFuelingRequest {

    @NotNull
    Integer carId;

    @NotNull
    Float state;

    @NotNull
    Float volume;

    @NotNull
    Float price;

}
