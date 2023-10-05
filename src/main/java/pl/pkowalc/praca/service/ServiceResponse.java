package pl.pkowalc.praca.service;

import lombok.Value;

@Value
class ServiceResponse {

    Integer id;

    String content;

    Float price;

    Float milage;

    Integer carId;

}
