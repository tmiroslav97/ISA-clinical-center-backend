package clinic.centersystem.model;

import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = DbTableConstants.CALENDAR)
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Personnel personnel;

    @JsonBackReference
    @OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CalendarItem> calendarItems = new HashSet<CalendarItem>();

}