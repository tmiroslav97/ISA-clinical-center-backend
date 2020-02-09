package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = DbTableConstants.PATIENT)
public class Patient extends User {

    @Column(name = DbColumnConstants.ISACTIVATED, nullable = false)
    private boolean isActivated;

    @Column(name = DbColumnConstants.ADDRESS, nullable = false)
    private String address;

    @Column(name = DbColumnConstants.COUNTRY, nullable = false)
    private String country;

    @Column(name = DbColumnConstants.CITY, nullable = false)
    private String city;

    @Column(name = DbColumnConstants.PHONENUM, nullable = false)
    private String phoneNum;

    @Column(name = DbColumnConstants.UNOIP, nullable = false)
    private String unoip;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Clinic> clinics = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<AppointmentRequirement> appointmentRequirements = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Surgery> surgeries = new HashSet<>();

    @Builder(builderMethodName = "patientBuilder")
    public Patient(Long id, String email, String password, String firstName, String lastName,
                   boolean enabled, boolean isFirstLog, Timestamp lastPasswordResetDate,
                   List<Authority> authorities, String address, String country, String city, String phoneNum, String unoip,
                   Set<Clinic> clinics, MedicalRecord medicalRecord, Set<Appointment> appointments, Set<AppointmentRequirement> appointmentRequirements,
                   Set<Surgery> surgeries, boolean isActivated, Long version) {
        super(id, email, password, firstName, lastName, enabled,isFirstLog, lastPasswordResetDate, authorities, version);
        this.address = address;
        this.country = country;
        this.city = city;
        this.phoneNum = phoneNum;
        this.unoip = unoip;
        this.clinics = clinics;
        this.medicalRecord = medicalRecord;
        this.appointments = appointments;
        this.appointmentRequirements = appointmentRequirements;
        this.surgeries = surgeries;
        this.isActivated = isActivated;
    }

}