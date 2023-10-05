package pl.pkowalc.praca.cars;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
interface CarRepository extends CrudRepository<CarEntity, Integer> {

    @Query("from CarEntity c left outer join c.allowedUsers u where c.username = ?1 or u.username = ?1")
    Set<CarEntity> findCars(String username);

    @Query("from CarEntity c left outer join c.allowedUsers u where (c.username = ?2 or u.username = ?2) AND c.id = ?1")
    Optional<CarEntity> findCarById(Integer id, String username);

    Optional<CarEntity> findByIdAndUsername(Integer id, String username);

    @Transactional
    void deleteByIdAndUsername(Integer integer, String username);

}
