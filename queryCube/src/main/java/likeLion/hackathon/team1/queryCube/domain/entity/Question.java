package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "deleted = false")
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

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question_sentence;

    // 날짜는 저절로 생성됨.
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(updatable = false)
    private LocalDateTime create_date;

    private Boolean isNotification_status;

    private boolean deleted = Boolean.FALSE;

    public static Question toQuestion(String question_sentence, Member questioner, Category category) {
        return Question.builder()
                .question_sentence(question_sentence)
                .questioner_id(questioner)
                .category_id(category)
                .isNotification_status(false) // 기본적으로 알림 상태를 false로 설정
                .build();
    }
}
