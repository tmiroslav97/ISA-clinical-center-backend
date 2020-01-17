package clinic.centersystem.model;


import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = DbTableConstants.NURSE)
public class Nurse extends Personnel {

    @JsonBackReference
    @OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<Prescription>();

    @Builder(builderMethodName = "nurseBuilder")
    public Nurse(Long id, String email, String password, String firstName, String lastName,
                 boolean enabled, RoleEnum role, boolean isFirstLog, Timestamp lastPasswordResetDate,
                 List<Authority> authorities,  Clinic clinic, Calendar calendar,
                 Set<AbsenceRequirement> absenceRequirements, Set<Appointment> appointments, Set<Prescription> prescriptions) {
        super(id, email, password, firstName, lastName, enabled, role, isFirstLog, lastPasswordResetDate, authorities, clinic, calendar, absenceRequirements, appointments);
        this.prescriptions = prescriptions;
    }
}