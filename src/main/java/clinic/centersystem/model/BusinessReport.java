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

    @Column(name = DbColumnConstants.CLINICRATING, unique = false, nullable = false)
    private Float clinicRating;

    @Column(name = DbColumnConstants.DOCTORRATING, unique = false, nullable = false)
    private Float doctorRating;

    @Column(name = DbColumnConstants.GRAPHIC, unique = false, nullable = false)
    private Float graphic;

    @Column(name = DbColumnConstants.INCOME, unique = false, nullable = false)
    private Float income;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    public BusinessReport() {
        // TODO: implement
    }

}