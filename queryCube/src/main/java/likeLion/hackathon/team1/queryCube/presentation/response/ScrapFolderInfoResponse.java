package likeLion.hackathon.team1.queryCube.presentation.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.ScrapFolderDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapFolderInfoResponse {

    private Long scrap_folder_id;
    private String scrap_folder_name;
    private Integer scrap_question_num;

    public static ScrapFolderInfoResponse from (ScrapFolderDto scrapFolderDto){
        return ScrapFolderInfoResponse.builder()
                .scrap_folder_id(scrapFolderDto.getScrap_folder_id())
                .scrap_folder_name(scrapFolderDto.getScrap_folder_name())
                .scrap_question_num(scrapFolderDto.getScrap_question_num())
                .build();
    }
}
