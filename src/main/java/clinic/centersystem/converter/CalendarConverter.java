package clinic.centersystem.converter;

import clinic.centersystem.dto.response.CalendarItemResponse;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.Calendar;
import clinic.centersystem.model.CalendarItem;
import clinic.centersystem.model.Surgery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class CalendarConverter {

    public static CalendarResponse toCreateCalendarResponseFromCalendar(Calendar calendar) {
        return CalendarResponse.builder()
                .id(calendar.getId())
                .calendarItemResponses(new HashSet<CalendarItemResponse>())
                .build();
    }

    public static CalendarItemResponse toCreateCalendarItemResponseFromCalendarItem(CalendarItem calendarItem) {
        return CalendarItemResponse.builder()
                .id(calendarItem.getId())
                .start(calendarItem.getStart())
                .end(calendarItem.getEnd())
                .title(calendarItem.getTitle())
                .type(calendarItem.getType())
                .typeId(calendarItem.getTypeId())
                .up_down_ind(calendarItem.getUp_down_ind())
                .build();
    }

    public static CalendarItem toCreateCalendarItemFromAppointmet(Appointment appointment){
        Date startDate = new java.util.Date(appointment.getStartTime()*1000L);
        Date endDate = new java.util.Date(appointment.getEndTime()*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateString = sdf.format(startDate);
        String endDateString = sdf.format(endDate);
        return CalendarItem.builder()
                .start(startDateString)
                .end(endDateString)
                .title(appointment.getType().getType())
                .up_down_ind("Y")
                .type("Appointment")
                .typeId(appointment.getId())
                .build();

    }

    public static CalendarItem toCreateCalendarItemFromSurgery(Surgery surgery){
        Date startDate = new java.util.Date(surgery.getStartTime()*1000L);
        Date endDate = new java.util.Date(surgery.getEndTime()*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateString = sdf.format(startDate);
        String endDateString = sdf.format(endDate);
        return CalendarItem.builder()
                .start(startDateString)
                .end(endDateString)
                .title("Surgery")
                .up_down_ind("Y")
                .typeId(surgery.getId())
                .type("sur")
                .build();

    }
}
