package pl.pkowalc.praca.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pl.pkowalc.praca.common.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDetailsManager userDetailsManager;
    private final UserRepository userRepository;

    public void register(RegisterUserRequest request) {
        if (userDetailsManager.userExists(request.getUsername())) {
            throw new UserExistsException();
        }
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users.username(request.getUsername())
                .password(request.getPassword())
                .roles("USER")
                .build();
        userDetailsManager.createUser(user);
    }

    public UserEntity getUser(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
