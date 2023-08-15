package likeLion.hackathon.team1.queryCube.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.entity.ScrapFolder;
import likeLion.hackathon.team1.queryCube.presentation.request.AddScrapFolderRequest;
import likeLion.hackathon.team1.queryCube.presentation.request.AddScrapMemoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapQuestionDto {

    private Long scrap_id;

    private ScrapFolder scrapFolderId;

    private Question questionId;

    private String scrap_memo;

    public static ScrapQuestionDto from(AddScrapMemoRequest request) {

        return ScrapQuestionDto.builder()
                .scrap_memo(request.getScrap_memo())
                .build();
    }
}
