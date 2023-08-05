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
public class ScrapFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrap_folder_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member memberId;

    private String scrap_folder_name;

    private boolean deleted = Boolean.FALSE;

    public static ScrapFolder toScrapFolder(ScrapFolderDto dto, Member user) {

        return ScrapFolder.builder()
                .memberId(user)
                .scrap_folder_name(dto.getScrap_folder_name())
                .build();


    }

}
