package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = DbTableConstants.BUSINESSREPORT)
public class BusinessReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.CLINICRATING, nullable = false)
    private Float clinicRating;

    @Column(name = DbColumnConstants.DOCTORRATING,  nullable = false)
    private Float doctorRating;

    @Column(name = DbColumnConstants.GRAPHIC,  nullable = false)
    private Float graphic;

    @Column(name = DbColumnConstants.INCOME,nullable = false)
    private Float income;

    @OneToOne(fetch = FetchType.LAZY)
    private Clinic clinic;

    public BusinessReport() {
        // TODO: implement
    }

}