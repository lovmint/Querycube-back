package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE answer SET deleted = true WHERE answer_id = ?")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member answerer_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question_id;

    @Column(nullable = false)
    private String answer_sentence;

    private String create_date;
}
