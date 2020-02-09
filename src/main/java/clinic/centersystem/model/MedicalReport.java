package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbTableConstants.MEDICALREPORT)
public class MedicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DESCRIPTION, nullable = false)
    private String description;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Diagnose diagnose;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "medicalReport", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();

}