package pl.pkowalc.praca.note;

import lombok.Value;

@Value
class NoteResponse {

    Integer id;

    String content;

    Float milage;

    Integer carId;

}
