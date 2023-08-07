package likeLion.hackathon.team1.queryCube.domain.entity;

        import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member liker;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    private LocalDateTime likeDate;

    public static QuestionLike toQuestionLike(Member liker, Question question) {
        QuestionLike questionLike = new QuestionLike();
        questionLike.setLiker(liker);
        questionLike.setQuestion(question);
        questionLike.setLikeDate(LocalDateTime.now());
        return questionLike;
    }
}
