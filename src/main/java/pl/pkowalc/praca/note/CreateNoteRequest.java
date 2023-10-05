package pl.pkowalc.praca.note;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
class CreateNoteRequest {

    @NotNull
    String content;

    @NotNull
    Float milage;

    @NotNull
    Integer carId;

}
