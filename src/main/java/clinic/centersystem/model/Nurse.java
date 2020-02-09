package clinic.centersystem.model;


import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "nurse", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<Prescription>();

    @Builder(builderMethodName = "nurseBuilder")
    public Nurse(Long id, String email, String password, String firstName, String lastName,
                 boolean enabled, boolean isFirstLog, Timestamp lastPasswordResetDate,
                 List<Authority> authorities, Clinic clinic, Calendar calendar,
                 Set<AbsenceHolidayRequirement> absenceHolidayRequirements, Set<Prescription> prescriptions, Long version) {
        super(id, email, password, firstName, lastName, enabled, isFirstLog, lastPasswordResetDate, authorities, clinic, calendar, absenceHolidayRequirements, version);
        this.prescriptions = prescriptions;
    }
}