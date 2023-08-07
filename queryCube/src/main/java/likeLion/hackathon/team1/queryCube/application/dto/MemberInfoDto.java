package likeLion.hackathon.team1.queryCube.application.dto;


        import com.fasterxml.jackson.annotation.JsonFormat;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {
    private Long member_id;
    private String name;
    private Integer reward_point;
    private LocalDateTime create_date;
}
