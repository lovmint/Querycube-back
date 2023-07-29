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
//@SQLDelete(sql = "UPDATE category_interest SET deleted = true WHERE category_interest_id = ?")
public class CategoryInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_interest_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member likerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Category categoryId;

    private boolean deleted = Boolean.FALSE;

    public static CategoryInterest toCategoryInterest(Member likerId, Category categoryId) {



        return CategoryInterest.builder()
                .likerId(likerId)
                .categoryId(categoryId)
                .build();


    }
}
