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
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = DbTableConstants.PERSONNEL)
public class Personnel extends User {

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    @JsonIgnore
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private Set<AbsenceHolidayRequirement> absenceHolidayRequirements;

    @Builder(builderMethodName = "personnelBuilder")
    public Personnel(Long id, String email, String password, String firstName, String lastName,
                     boolean enabled, boolean isFirstLog, Timestamp lastPasswordResetDate,
                     List<Authority> authorities, Clinic clinic, Calendar calendar,
                     Set<AbsenceHolidayRequirement> absenceHolidayRequirements, Long version) {
        super(id, email, password, firstName, lastName, enabled, isFirstLog, lastPasswordResetDate, authorities, version);
        this.clinic = clinic;
        this.calendar = calendar;
        this.absenceHolidayRequirements = absenceHolidayRequirements;
    }


}
