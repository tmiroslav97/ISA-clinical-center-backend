package clinic.centersystem.model;

import clinic.centersystem.common.db.DbTableConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.MEDICINERECORD)
public class MedicineRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Set<Medicine> medicines = new HashSet<Medicine>();

}
