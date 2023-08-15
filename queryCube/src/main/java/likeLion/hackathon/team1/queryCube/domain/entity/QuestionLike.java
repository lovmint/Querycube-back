package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_like_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member likerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Question questionId;

    private boolean deleted = Boolean.FALSE;

    public static QuestionLike toQuestionLike(Member likerId, Question questionId) {
        return QuestionLike.builder()
                .likerId(likerId)
                .questionId(questionId)
                .build();
    }
}
