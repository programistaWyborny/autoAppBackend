package pl.pkowalc.praca.cars;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import pl.pkowalc.praca.fueling.FuelingEntity;
import pl.pkowalc.praca.note.NoteEntity;
import pl.pkowalc.praca.user.UserEntity;
import pl.pkowalc.praca.service.ServiceEntity;

@Getter
@Setter
@ToString
@Table(name = "car")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String brand;

    private String model;

    private Integer year;

    private String description;

    private String username;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_car",
            joinColumns = @JoinColumn(name = "shared_car_id"),
            inverseJoinColumns = @JoinColumn(name = "allowed_username")) @ToString.Exclude
    private Set<UserEntity> allowedUsers;

    //na bazie car nie wie o istnieniu notatki, ale w javie mozna je zlaczyc
    //dzieki dodaniu mapowania z tej strony i ustawieniu orphanRemoval
    //usuniecie samochodu powoduje usuniecie powiazanych z nim notatek
    @OneToMany(mappedBy = "car", orphanRemoval = true, cascade = CascadeType.ALL) @ToString.Exclude
    private Set<NoteEntity> notes;

    @OneToMany(mappedBy = "car", orphanRemoval = true, cascade = CascadeType.ALL) @ToString.Exclude
    private Set<FuelingEntity> fuelings;

    @OneToMany(mappedBy = "car", orphanRemoval = true, cascade = CascadeType.ALL) @ToString.Exclude
    private Set<ServiceEntity> services;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarEntity carEntity = (CarEntity) o;
        return id != null && Objects.equals(id, carEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

