package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = DbTableConstants.SURGERY)
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DATETIME, nullable = false)
    private Long dateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SurgExRoom surgExRoom;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @Column(name = DbColumnConstants.STARTTIME, nullable = false)
    private Long startTime;

    @Column(name = DbColumnConstants.ENDTIME, nullable = false)
    private Long endTime;

    @Column(name = DbColumnConstants.DURATION, nullable = false)
    private Long duration;

    public Surgery() {
        // TODO: implement
    }


}