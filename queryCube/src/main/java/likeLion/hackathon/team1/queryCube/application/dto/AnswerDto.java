package likeLion.hackathon.team1.queryCube.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.service.AnswerService;
import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    private Long answer_id;

    private Member answerer_id;

    private Question question_id;

    private String answer_sentence;

    private Integer answer_like_num;

    private LocalDateTime create_date;

    private Boolean isActive;

    private Boolean isQuestioner_selection;

    private Boolean isLike_active;



    public static AnswerDto toAdd(AddAnswerRequest request) {
        return AnswerDto.builder()
                .answer_sentence(request.getAnswer_sentence())
                .answer_like_num(0)
                .isActive(false)
                .isQuestioner_selection(false)
                .build();
    }

    public static AnswerDto from(AddAnswerRequest request) {
        return AnswerDto.builder()
                .answer_sentence(request.getAnswer_sentence())
                .build();
    }


    public static AnswerDto from(Answer answer) {


        return AnswerDto.builder()
                .answer_id(answer.getAnswer_id())
                .answerer_id(answer.getAnswerer_id())
                .question_id(answer.getQuestion_id())
                .answer_sentence(answer.getAnswer_sentence())
                .answer_like_num(answer.getAnswer_like_num())
                .create_date(answer.getCreate_date())
                .isActive(answer.getIsActive())
                .isQuestioner_selection(answer.getIsQuestioner_selection())
                .build();
    }
}
