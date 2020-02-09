package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.AppStateEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbTableConstants.APPREQUIREMENT)
public class AppointmentRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DATETIME, nullable = false)
    private Long dateTime;

    @Column(name = DbColumnConstants.APPSTATE,  nullable = false)
    private AppStateEnum appState;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

}
