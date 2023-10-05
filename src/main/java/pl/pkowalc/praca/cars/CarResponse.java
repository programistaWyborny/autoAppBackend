package pl.pkowalc.praca.cars;

import lombok.Value;

@Value
class CarResponse {

  int id;

  String name;

  String brand;

  String model;

  Integer year;

  String description;

  String username;

}
