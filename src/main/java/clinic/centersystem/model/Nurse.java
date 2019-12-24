package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.RoleEnum;
import clinic.centersystem.model.Calendar;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Recepie> recepies = new HashSet<Recepie>();

    @Builder(builderMethodName = "nurseBuilder")
    public Nurse(Long id, String email, String password, String firstName, String lastName,
                 boolean enabled, RoleEnum role, boolean isFirstLog, Timestamp lastPasswordResetDate,
                 List<Authority> authorities, Set<Patient> patients, Clinic clinic, Calendar calendar,
                 Set<AbsenceRequirement> absenceRequirements, Set<Appointment> appointments, Set<Recepie> recepies) {
        super(id, email, password, firstName, lastName, enabled, role, isFirstLog, lastPasswordResetDate, authorities, patients, clinic, calendar, absenceRequirements, appointments);
        this.recepies = recepies;
    }
}