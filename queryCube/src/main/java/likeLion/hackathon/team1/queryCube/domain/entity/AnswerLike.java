package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE answerLike SET deleted = true WHERE answer_like_id = ?")
public class AnswerLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_like__id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member liker_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer_id;

    private boolean deleted = Boolean.FALSE;

}
