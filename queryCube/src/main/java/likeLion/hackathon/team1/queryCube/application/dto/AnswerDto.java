package likeLion.hackathon.team1.queryCube.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto {

    private Long answer_id;

    private Member answerer_id;

    private Question question_id;

    private String answer_sentence;

    private LocalDateTime create_date;

    private Boolean isActive;

    private Boolean isQuestioner_like;


    public static AnswerDto toAdd(AddAnswerRequest request) {
        return AnswerDto.builder()
                .answer_sentence(request.getAnswer_sentence())
                .isActive(false)
                .isQuestioner_like(false)
                .build();
    }
}
