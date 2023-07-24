package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted = false")
//@SQLDelete(sql = "UPDATE answer_like SET deleted = true WHERE answer_like_id = ?")
public class AnswerLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_like_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member likerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answerId;

    private boolean deleted = Boolean.FALSE;

    public static AnswerLike toAnswerLike(Member likerId, Answer answerId) {



        return AnswerLike.builder()
                .likerId(likerId)
                .answerId(answerId)
                .build();


    }
}
