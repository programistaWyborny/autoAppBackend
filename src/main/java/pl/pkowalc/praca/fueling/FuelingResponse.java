package pl.pkowalc.praca.fueling;

import lombok.Value;

@Value
class FuelingResponse {

    Integer id;

    Float state;

    Float volume;

    Float price;

    Integer carId;

}
