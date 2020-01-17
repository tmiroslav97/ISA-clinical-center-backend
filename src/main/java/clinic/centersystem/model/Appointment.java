package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.AppStateEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = DbTableConstants.APPOINTMENT)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DATETIME, unique = false, nullable = false)
    private Long dateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppointmentType type;

    @Column(name = DbColumnConstants.APPSTATE, unique = false, nullable = false)
    private AppStateEnum appState;

    @Column(name = DbColumnConstants.STARTTIME, unique = false, nullable = false)
    private Long startTime;

    @Column(name = DbColumnConstants.ENDTIME, unique = false, nullable = false)
    private Long endTime;

    @Column(name = DbColumnConstants.DURATION, unique = false, nullable = false)
    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SurgExRoom surgExRoom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Personnel personnel;

    @Column(name = DbColumnConstants.PRICE, unique = false, nullable = false)
    private Float price;

    @Column(name = DbColumnConstants.DISCOUNT, unique = false, nullable = false)
    private Float discount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MedicalReport medicalReport;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Appointment() {
        // TODO: implement
    }

}