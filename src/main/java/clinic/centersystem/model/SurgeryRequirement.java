package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.SURGERYREQUIREMENT)
public class SurgeryRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DATE, nullable = false)
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime date;

    @Column(name = DbColumnConstants.TERMIN, nullable = false)
    private Integer termin;

    @Column(name = DbColumnConstants.PATIENTID, nullable = false)
    private Long patientId;

    @Column(name = DbColumnConstants.PATIENTNAME, nullable = false)
    private String patientName;

    @Column(name = DbColumnConstants.DOCTORID, nullable = false)
    private Long doctorId;

    @Column(name = DbColumnConstants.DOCTORNAME, nullable = false)
    private String doctorName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    @Version
    private Long version = 0L;
}
