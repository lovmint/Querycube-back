package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE answer SET deleted = true WHERE answer_id = ?")
public class Answer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member answerer_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question_id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer_sentence;

    private Integer answer_like_num;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(updatable = false)
    private LocalDateTime create_date;

    private Boolean isActive;

    private Boolean isQuestioner_like;

    private boolean deleted = Boolean.FALSE;

    public static Answer toAnswer(AnswerDto dto, Question room, Member user) {



        return Answer.builder()
                .question_id(room)
                .answerer_id(user)
                .answer_sentence(dto.getAnswer_sentence())
                .answer_like_num(dto.getAnswer_like_num())
                .isActive(dto.getIsActive())
                .isQuestioner_like(dto.getIsQuestioner_like())
                .build();


    }

}
