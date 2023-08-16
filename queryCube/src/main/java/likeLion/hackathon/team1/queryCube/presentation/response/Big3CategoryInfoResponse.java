package likeLion.hackathon.team1.queryCube.presentation.response;

import likeLion.hackathon.team1.queryCube.application.dto.Big3CategoryDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Category;

import java.util.ArrayList;
import java.util.List;

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
public class Big3CategoryInfoResponse {

    private Long big3_category_id;
    private String name;
    private List<Category> categoryList = new ArrayList<>();

    public static Big3CategoryInfoResponse from(Big3CategoryDto categoryDto) {
        return Big3CategoryInfoResponse.builder()
                .big3_category_id(categoryDto.getBig3_category_id())
                .name(categoryDto.getName())
                .categoryList(categoryDto.getCategoryList())
                .build();
    }

}
