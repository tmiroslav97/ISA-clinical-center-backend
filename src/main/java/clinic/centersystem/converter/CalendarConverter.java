package clinic.centersystem.converter;

import clinic.centersystem.dto.response.CalendarItemResponse;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.Calendar;
import clinic.centersystem.model.CalendarItem;
import clinic.centersystem.model.Surgery;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class CalendarConverter {

    public static CalendarResponse toCreateCalendarResponseFromCalendar(Calendar calendar) {
        return CalendarResponse.builder()
                .id(calendar.getId())
                .calendarItemResponses(new HashSet<>())
                .build();
    }

    public static CalendarItemResponse toCreateCalendarItemResponseFromCalendarItem(CalendarItem calendarItem) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
        String start = dtf.print(calendarItem.getStart());
        String end = dtf.print(calendarItem.getEnd());
        return CalendarItemResponse.builder()
                .id(calendarItem.getId())
                .start(start)
                .end(end)
                .title(calendarItem.getTitle())
                .type(calendarItem.getType())
                .typeId(calendarItem.getTypeId())
                .allday(calendarItem.getAllDay())
                .build();
    }

    public static CalendarItem toCreateCalendarItemFromAppointmet(Appointment appointment) {
        //metoda za pravljenje calendar itema od pregleda
        return null;
    }

    public static CalendarItem toCreateCalendarItemFromSurgery(Surgery surgery) {
        //metoda za pravljenje calendar itema od operacije
        return null;

    }
}
