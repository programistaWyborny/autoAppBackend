package pl.pkowalc.praca.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends CrudRepository<UserEntity, String> {
}
