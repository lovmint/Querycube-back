package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.ScrapFolderDto;
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
public class ScrapQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrap_question_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private ScrapFolder scrapFolderId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Question questionId;

    private String scrap_memo;

    private boolean deleted = Boolean.FALSE;

    public static ScrapQuestion toScrapQuestion(ScrapFolder scrapFolderId, Question questionId) {



        return ScrapQuestion.builder()
                .scrapFolderId(scrapFolderId)
                .questionId(questionId)
                .build();


    }
}
