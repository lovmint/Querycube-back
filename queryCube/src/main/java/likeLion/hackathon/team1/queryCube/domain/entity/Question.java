package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE question SET deleted = true WHERE question_id = ?")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member questioner_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category_id;

    @Column(nullable = false)
    private String question_sentence;

    private String create_date;
}
