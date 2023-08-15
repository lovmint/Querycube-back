package likeLion.hackathon.team1.queryCube.application.dto;

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
public class QuestionDto {
    private Long question_id;
    private String question_title;
    private String question_content;
    private Long questioner_id;
    private LocalDateTime create_date;
    private int questionLikeCount;
    private List<String> imageUrls;
    private String userImage; // Field for user image URL

    // Add other fields as needed


}
