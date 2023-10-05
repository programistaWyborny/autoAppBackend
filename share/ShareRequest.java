package pl.pkowalc.praca.share;

import lombok.Value;

@Value
class ShareRequest {

    Integer carId;

    String sharedWithUsername;

}
