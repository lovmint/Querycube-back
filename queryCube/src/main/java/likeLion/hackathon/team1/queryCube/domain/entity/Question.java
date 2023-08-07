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
import java.util.ArrayList;
import java.util.List;

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
    private String question_title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question_content;

    // 이미지 첨부 파일 리스트
    @ElementCollection
    @CollectionTable(name = "question_images", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "image_url", nullable = false)
    private List<String> imageUrls = new ArrayList<>();

    // 날짜 자동 생성
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(updatable = false)
    private LocalDateTime create_date;

    private Boolean isNotification_status;

    private boolean deleted = Boolean.FALSE;

    // 이미지 업로드 기능 추가
    public void uploadImageToNaverCloud(String imageUrl) {
        // 네이버 클라우드 API 호출하여 이미지 업로드 구현
        // 구현 방법은 클라우드 API 사용 라이브러리 등에 따라 달라질 수 있습니다.
        // 이 예시에서는 이미지 URL을 imageUrls 리스트에 추가하는 것으로 가정합니다.
        if (imageUrls.size() < 5) {
            imageUrls.add(imageUrl);
        } else {
            // 이미지 첨부 파일이 5장을 초과하면 에러 처리 또는 알림 로직 추가
            // 이 예시에서는 간단히 로그를 출력합니다.
            System.out.println("이미지 첨부 파일은 최대 5장까지 가능합니다.");
        }
    }

    // 질문 등록 기능 추가
    public void registerQuestion(Member questioner, Category category, String title, String content) {
        this.questioner_id = questioner;
        this.category_id = category;
        this.question_title = title;
        this.question_content = content;
        this.create_date = LocalDateTime.now();
        this.isNotification_status = false;
        // 이미지 첨부 파일을 업로드하는 기능은 다른 메서드에서 처리되므로 여기에는 추가하지 않음.
    }

    // 질문 삭제 기능 추가
    public void deleteQuestion() {
        this.deleted = true;
    }

    // 질문 수정 기능 추가
    public void updateQuestion(String newTitle, String newContent) {
        this.question_title = newTitle;
        this.question_content = newContent;
        // 수정일자 업데이트
        this.create_date = LocalDateTime.now();
    }

    public String getQuestion_sentence() {
        return question_title;
    }

    // Add the following relationship mapping and fields for QuestionLike

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionLike> questionLikes = new ArrayList<>();

    // 질문 좋아요 등록 기능 추가
    public void addQuestionLike(Member liker) {
        QuestionLike questionLike = QuestionLike.toQuestionLike(liker, this);
        questionLikes.add(questionLike);
    }

    // 질문 좋아요 취소 기능 추가
    public void removeQuestionLike(Member liker) {
        QuestionLike questionLike = findQuestionLikeByLiker(liker);
        if (questionLike != null) {
            questionLikes.remove(questionLike);
        }
    }

    // 질문 좋아요 여부 확인 기능 추가
    public boolean isQuestionLikedBy(Member liker) {
        return findQuestionLikeByLiker(liker) != null;
    }

    private QuestionLike findQuestionLikeByLiker(Member liker) {
        for (QuestionLike questionLike : questionLikes) {
            if (questionLike.getLiker().equals(liker)) {
                return questionLike;
            }
        }
        return null;
    }

    public int getQuestionLikeCount() {
        return questionLikes.size();
    }
}
