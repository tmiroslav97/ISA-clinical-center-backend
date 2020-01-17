package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.CODEBOOK)
public class Codebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, nullable = false)
    private String name;

    @Column(name = DbColumnConstants.CODE, unique = true, nullable = false)
    private String code;


    @Column(name = DbColumnConstants.TYPE, nullable = false)
    private String type;

    @Column(name = DbColumnConstants.DESCRIPTION, nullable = false)
    private String description;

}
