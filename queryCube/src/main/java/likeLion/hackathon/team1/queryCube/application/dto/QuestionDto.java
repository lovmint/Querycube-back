package likeLion.hackathon.team1.queryCube.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long question_id;
    private String question_title;
    private String question_content;
    // Add other fields as needed
}