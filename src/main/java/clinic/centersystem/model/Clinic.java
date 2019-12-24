package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.w3c.dom.views.DocumentView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbTableConstants.CLINIC)
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;

    @Column(name = DbColumnConstants.ADDRESS, unique = false, nullable = false)
    private String address;

    @Column(name = DbColumnConstants.DESCRIPTION, unique = false, nullable = false)
    private String description;

    @Column(name = DbColumnConstants.SUMRATING, unique = false, nullable = false)
    private Float sumRating;

    @Column(name = DbColumnConstants.CNTRATING, unique = false, nullable = false)
    private Float cntRating;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BusinessReport busReport;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PriceList priceList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Doctor> doctors = new HashSet<Doctor>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Nurse> nurses = new HashSet<Nurse>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClinicAdmin> clinicAdmins = new HashSet<ClinicAdmin>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SurgExRoom> surExRooms = new HashSet<SurgExRoom>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Patient> patients = new HashSet<Patient>();

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AbsenceRequirement> reqAbs = new HashSet<AbsenceRequirement>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AppointmentRequirement> appReqs = new HashSet<AppointmentRequirement>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SurgeryRequirement> surReqs = new HashSet<SurgeryRequirement>();


}