package pl.pkowalc.praca.cars;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
class CreateCarRequest {

  @NotNull
  String name;

  @NotNull
  String brand;

  @NotNull
  String model;

  @NotNull
  Integer year;

  @NotNull
  String description;


}
