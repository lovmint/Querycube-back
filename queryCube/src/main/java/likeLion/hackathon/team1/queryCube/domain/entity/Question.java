package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
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

    // 이미지 업로드 기능 추가 (네이버 클라우드)
    public void uploadImageToNaverCloud(String imagePath) {
        // 네이버 클라우드 API 업로드 URL
        String uploadUrl = "https://ncloud.apigw.ntruss.com/cv/v1/ncp-imageocr/v1/upload";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uploadUrl);
            httpPost.addHeader("X-OCR-SECRET", "YOUR_NAVER_CLOUD_API_KEY");

            File imageFile = new File(imagePath);
            FileEntity fileEntity = new FileEntity(imageFile, ContentType.DEFAULT_BINARY);

            httpPost.setEntity(fileEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                // 처리할 응답 내용을 여기서 처리 (예: 이미지 URL 파싱)
                if (response.getStatusLine().getStatusCode() == 200 && responseEntity != null) {
                    String uploadedImageUrl = EntityUtils.toString(responseEntity);
                    if (imageUrls.size() < 5) {
                        imageUrls.add(uploadedImageUrl);
                    } else {
                        System.out.println("이미지 첨부 파일은 최대 5장까지 가능합니다.");
                    }
                } else {
                    System.out.println("이미지 업로드 실패: " + response.getStatusLine().getStatusCode());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
