package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.MEDICINE)
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = false, nullable = false)
    private String name;

    @Column(name = DbColumnConstants.CODE, unique = true, nullable = false)
    private String code;

    @Column(name = DbColumnConstants.DESCRIPTION, unique = true, nullable = false)
    private String description;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private MedicineRecord medicineRecord;
}