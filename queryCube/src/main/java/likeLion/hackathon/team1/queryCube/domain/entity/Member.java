package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import likeLion.hackathon.team1.queryCube.application.dto.MemberInfoDto;
import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto; // Import QuestionDto

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;


    private String username;
    private String password; // Add this property

    private String name;
    private Integer reward_point;
    private String googleId;

    private String imageUrl; // Add the imageUrl field

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(updatable = false)
    private LocalDateTime create_date;

    private boolean deleted = Boolean.FALSE;

    // Add the getImageUrl method
    public String getImageUrl() {
        return imageUrl;
    }

    // 구글 계정 연동 간편 로그인 기능
    private String googleAccountId;
    private String displayName;

    public void googleLogin(String googleAccountId, String displayName) {
        this.googleAccountId = googleAccountId;
        this.displayName = displayName;
    }

    // 로그아웃 기능
    public void logout() {
        this.googleAccountId = null;
        this.displayName = null;
    }

    // 마이페이지 사용자 정보 보여주기 기능
    public MemberInfoDto getMyPageInfo() {
        return new MemberInfoDto(this.member_id, this.name, this.reward_point, this.create_date);
    }

    // 마이페이지 사용자 정보 수정하기 기능
    public void updateMyPageInfo(String newName) {
        this.name = newName;
    }

    // 사용자가 작성한 질문리스트 보여주기 기능
    @OneToMany(mappedBy = "questioner_id")
    private List<Question> questions = new ArrayList<>();

    public List<QuestionDto> getMyQuestions() {
        return this.questions.stream()
                .map(question -> new QuestionDto(question.getQuestion_id(), question.getQuestion_title(), question.getQuestion_content(), question.getQuestioner_id().getMember_id(), question.getCreate_date(), question.getQuestionLikes().size(),question.getImageUrls(),question.getQuestioner_id().getImageUrl()))
                .collect(Collectors.toList());
    }


}
