package likeLion.hackathon.team1.queryCube.presentation.response;

        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.time.LocalDateTime;
        import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfoResponse {
    private Long questionId;
    private String questionTitle;
    private String questionContent;
    private Long questionerId;
    private LocalDateTime createDate;
    private int questionLikeCount;
    private List<String> imageUrls;
    // Add other fields as needed
}







