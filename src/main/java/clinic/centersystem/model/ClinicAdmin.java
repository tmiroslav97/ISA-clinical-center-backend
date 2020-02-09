package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.RoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = DbTableConstants.CLINICADMIN)
public class ClinicAdmin extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    @Builder(builderMethodName = "clinicAdminBuilder")
    public ClinicAdmin(Long id, String email, String password, String firstName, String lastName,
                             boolean enabled, boolean isFirstLog, Timestamp lastPasswordResetDate,
                             List<Authority> authorities, Long version) {
        super(id, email, password, firstName, lastName, enabled, isFirstLog, lastPasswordResetDate, authorities, version);
    }

}