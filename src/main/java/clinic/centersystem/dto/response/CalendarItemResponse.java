package clinic.centersystem.dto.response;


import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarItemResponse {
    private Long id;
    private String title;
    private String start;
    private String end;
    private String type;
    private Long typeId;
    private String up_down_ind;
}
