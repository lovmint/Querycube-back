package likeLion.hackathon.team1.queryCube.presentation.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionInfoResponse {
    private Long question_id;
    private String question_title;
    private String question_content;
    private Long questioner_id;
    private LocalDateTime create_date;
    private int questionLikeCount;
    // Add other fields as needed
}







