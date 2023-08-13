package likeLion.hackathon.team1.queryCube.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddQuestionRequest {
    private Long questionerId;
    private Long categoryId;
    private String questionTitle;
    private String questionContent;
    private List<String> imageUrls;
    // Add other fields as needed
}

