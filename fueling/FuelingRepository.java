package pl.pkowalc.praca.fueling;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FuelingRepository extends CrudRepository<FuelingEntity, Integer> {

    @Query("select n from FuelingEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where c.username = ?1 or au.username = ?1")
    Set<FuelingEntity> findByUsername(String username);

    @Query("select n from FuelingEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where (c.username = ?2 or au.username = ?2) and n.id = ?1")
    Optional<FuelingEntity> findByIdAndUsername(Integer id, String username);

    @Query("select n from FuelingEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where (c.username = ?2 or au.username = ?2) and c.id = ?1")
    Set<FuelingEntity> findByCarIdAndUsername(Integer carId, String username);

}
