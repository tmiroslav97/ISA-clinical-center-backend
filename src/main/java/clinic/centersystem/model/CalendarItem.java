package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

    @Column(name = DbColumnConstants.TITLE, nullable = false)
    private String title;

    @Column(name = DbColumnConstants.START, nullable = false)
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime start;

    @Column(name = DbColumnConstants.END, nullable = false)
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    private DateTime end;

    @Column(name = DbColumnConstants.TYPE)
    private String type;

    @Column(name = DbColumnConstants.TYPEID)
    private Long typeId;

    @Column(name = DbColumnConstants.ALLDAY, nullable = false)
    private String allDay;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

}
