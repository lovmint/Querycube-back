package likeLion.hackathon.team1.queryCube.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;

import likeLion.hackathon.team1.queryCube.domain.entity.ScrapFolder;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import likeLion.hackathon.team1.queryCube.presentation.request.AddScrapFolderRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapFolderDto {

    private Long scrap_folder_id;

    private Member memberId;

    private String scrap_folder_name;

    private Integer scrap_question_num;

    private boolean deleted = Boolean.FALSE;

    public static ScrapFolderDto toAddScrapFolder(AddScrapFolderRequest request) {
        return ScrapFolderDto.builder()
                .scrap_folder_name(request.getScrap_folder_name())
                .scrap_question_num(0)
                .build();
    }

    public static ScrapFolderDto from(AddScrapFolderRequest request) {

        return ScrapFolderDto.builder()
                .scrap_folder_name(request.getScrap_folder_name())
                .build();
    }
    public static ScrapFolderDto from(ScrapFolder scrapFolder) {

        return ScrapFolderDto.builder()
                .scrap_folder_id(scrapFolder.getScrap_folder_id())
                .memberId(scrapFolder.getMemberId())
                .scrap_folder_name(scrapFolder.getScrap_folder_name())
                .scrap_question_num(scrapFolder.getScrap_question_num())
                .build();
    }
}
