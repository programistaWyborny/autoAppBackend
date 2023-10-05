package pl.pkowalc.praca.user;

import javax.validation.constraints.NotNull;

import lombok.Value;

@Value
class RegisterUserRequest {

    @NotNull
    String username;

    @NotNull
    String password;

}
