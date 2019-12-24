package clinic.centersystem.dto.response;

import lombok.*;

import java.util.Set;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {

    private Long id;

    private Set<CalendarItemResponse> calendarItemResponses;
}
