package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Builder
@Getter
@Setter
@Entity
@Table(name = DbTableConstants.SURGEXROOM)
public class SurgExRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NUMBER, nullable = false)
    private Integer number;

    @Column(name = DbColumnConstants.NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    @Builder(builderMethodName = "surgExRoomBuilder")
    public SurgExRoom(Long id, Integer number, String name, Clinic clinic) {
        // TODO: implement
        this.id=id;
        this.number=number;
        this.name=name;
        this.clinic=clinic;
    }


}