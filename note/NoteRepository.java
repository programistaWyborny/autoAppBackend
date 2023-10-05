package pl.pkowalc.praca.note;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface NoteRepository extends CrudRepository<NoteEntity, Integer> {

    @Query("select n from NoteEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where c.username = ?1 or au.username = ?1")
    Set<NoteEntity> findByUsername(String username);

    @Query("select n from NoteEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where (c.username = ?2 or au.username = ?2) and n.id = ?1")
    Optional<NoteEntity> findByIdAndUsername(Integer id, String username);

    @Query("select n from NoteEntity n " +
            "left join CarEntity c on n.car.id = c.id " +
            "left join c.allowedUsers au " +
            "where (c.username = ?2 or au.username = ?2) and c.id = ?1")
    Set<NoteEntity> findByCarIdAndUsername(Integer carId, String username);

}
