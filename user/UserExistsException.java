package pl.pkowalc.praca.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
class UserExistsException extends RuntimeException {
}
