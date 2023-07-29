package likeLion.hackathon.team1.queryCube.presentation.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
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
public class AnswerInfoResponse {

    private Long answer_id;
    //private Long answerer_id;
    private Long question_id;
    private String question_sentence;
    private String answerer_name;
    private String answer_sentence;
    private Integer answer_like_num;
    private Boolean isActive;
    private Boolean isQuestioner_like;
    private Boolean isLike_active;
    private LocalDateTime create_date;

    public static AnswerInfoResponse from (AnswerDto answerDto){
        return AnswerInfoResponse.builder()
                .answer_id(answerDto.getAnswer_id())
                .question_id(answerDto.getQuestion_id().getQuestion_id())
                .answerer_name(answerDto.getAnswerer_id().getName())
                .question_sentence(answerDto.getQuestion_id().getQuestion_sentence())
                .answer_sentence(answerDto.getAnswer_sentence())
                .answer_like_num(answerDto.getAnswer_like_num())
                .create_date(answerDto.getCreate_date())
                .isActive(answerDto.getIsActive())
                .isQuestioner_like(answerDto.getIsQuestioner_like())
                .isLike_active(answerDto.getIsLike_active())
                .build();
    }
}
