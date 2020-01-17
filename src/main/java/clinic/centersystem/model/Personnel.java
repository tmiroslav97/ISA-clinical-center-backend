package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
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
@Table(name = DbTableConstants.PERSONNEL)
public class Personnel extends User {

    @Builder(builderMethodName = "personnelBuilder")
    public Personnel(Long id, String email, String password, String firstName, String lastName,
                     boolean enabled, RoleEnum role, boolean isFirstLog, Timestamp lastPasswordResetDate,
                     List<Authority> authorities, Clinic clinic, Calendar calendar,
                     Set<AbsenceRequirement> absenceRequirements, Set<Appointment> appointments) {
        super(id, email, password, firstName, lastName, enabled, role, isFirstLog, lastPasswordResetDate, authorities);
        this.clinic = clinic;
        this.calendar = calendar;
        this.absenceRequirements = absenceRequirements;
        this.appointments = appointments;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    @JsonBackReference
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AbsenceRequirement> absenceRequirements;

    @OneToMany(mappedBy = "personnel",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

}
