package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbTableConstants.CALENDARITEM)
public class CalendarItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.TITLE, unique = false, nullable = false)
    private String title;

    @Column(name = DbColumnConstants.START, unique = false, nullable = false)
    private String start;

    @Column(name = DbColumnConstants.END, unique = false, nullable = false)
    private String end;

    @Column(name = DbColumnConstants.TYPE, unique = false, nullable = true)
    private String type;

    @Column(name = DbColumnConstants.TYPEID, unique = false, nullable = true)
    private Long typeId;

    @Column(name = DbColumnConstants.UDI, unique = false, nullable = false)
    private String up_down_ind;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Calendar calendar;

}
