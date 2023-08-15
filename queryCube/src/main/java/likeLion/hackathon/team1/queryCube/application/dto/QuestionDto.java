package likeLion.hackathon.team1.queryCube.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.domain.entity.Category;
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
public class QuestionDto {

    private Long question_id;

    private Member questioner_id;

    private Category category_id;

    private String question_sentence;

    private LocalDateTime create_date;

    private Boolean isNotification_status;

    private boolean deleted;

    public static QuestionDto from(Question question) {
        return QuestionDto.builder()
                .question_id(question.getQuestion_id())
                .questioner_id(question.getQuestioner_id())
                .category_id(question.getCategory_id())
                .question_sentence(question.getQuestion_sentence())
                .create_date(question.getCreate_date())
                .isNotification_status(question.getIsNotification_status())
                .deleted(question.isDeleted())
                .build();
    }

}
