package likeLion.hackathon.team1.queryCube.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddQuestionRequest {
    private String title;
    private String content;
    // Add other fields as needed
}
