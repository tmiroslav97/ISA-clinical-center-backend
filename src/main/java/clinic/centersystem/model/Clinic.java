package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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

    @Column(name = DbColumnConstants.ADDRESS, nullable = false)
    private String address;

    @Column(name = DbColumnConstants.DESCRIPTION, nullable = false)
    private String description;

    @Column(name = DbColumnConstants.SUMRATING, nullable = false)
    private Float sumRating;

    @Column(name = DbColumnConstants.CNTRATING, nullable = false)
    private Float cntRating;

    @OneToOne(fetch = FetchType.LAZY)
    private BusinessReport busReport;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<PriceListItem> priceList = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<Nurse> nurses = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<ClinicAdmin> clinicAdmins = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<AppointmentType> appointmentTypes = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<AbsenceHolidayRequirement> reqAbs = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<AppointmentRequirement> appReqs = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<SurgeryRequirement> surReqs = new HashSet<>();

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();

}