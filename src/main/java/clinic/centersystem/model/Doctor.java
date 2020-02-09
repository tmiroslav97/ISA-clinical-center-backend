package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
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
@Table(name = DbTableConstants.DOCTOR)
public class Doctor extends Personnel {

    @Column(name = DbColumnConstants.SUMRATING, nullable = false)
    private Float sumRating;

    @Column(name = DbColumnConstants.CNTRATING, nullable = false)
    private Integer cntRating;

    @Column(name = DbColumnConstants.STARTTIME, nullable = false)
    private Integer startTime;

    @Column(name = DbColumnConstants.ENDTIME, nullable = false)
    private Integer endTime;

    @JsonBackReference
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Surgery> surgeries = new HashSet<>();

    @Builder(builderMethodName = "doctorBuilder")
    public Doctor(Long id, String email, String password, String firstName, String lastName,
                  boolean enabled, boolean isFirstLog, Timestamp lastPasswordResetDate,
                  List<Authority> authorities, Clinic clinic, Calendar calendar,
                  Set<AbsenceHolidayRequirement> absenceHolidayRequirements, Set<Appointment> appointments, Set<Surgery> surgeries, Float sumRating,
                  Integer cntRating, Integer startTime, Integer endTime, Long version) {
        super(id, email, password, firstName, lastName, enabled, isFirstLog, lastPasswordResetDate, authorities, clinic, calendar, absenceHolidayRequirements, version);
        this.sumRating = sumRating;
        this.cntRating = cntRating;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointments = appointments;
        this.surgeries = surgeries;
    }
}