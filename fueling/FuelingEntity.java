package pl.pkowalc.praca.fueling;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import pl.pkowalc.praca.cars.CarEntity;

@Getter
@Setter
@ToString
@Table(name = "fueling")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuelingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float milage;

    private Float volume;

    private Float price;

    @JoinColumn(name = "car_id")
    @ManyToOne()
    private CarEntity car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FuelingEntity that = (FuelingEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
