package clinic.centersystem.model;

import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    private Personnel personnel;


    @JsonIgnore
    @OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<CalendarItem> calendarItems = new HashSet<>();

}