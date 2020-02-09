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
@Table(name = DbTableConstants.MEDICALRECORD)
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DESCRIPTION, nullable = false)
    private String description;

    @Column(name = DbColumnConstants.HEIGHT)
    private Float height;

    @Column(name = DbColumnConstants.WEIGHT)
    private Float weight;

    @Column(name = DbColumnConstants.BLOODTYPE)
    private String bloodType;

    @JsonIgnore
    @OneToOne( fetch = FetchType.LAZY)
    private Patient patient;

    @JsonIgnore
    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private Set<MedicalReport> diseaseHistory = new HashSet<>();

}