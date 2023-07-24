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
@SQLDelete(sql = "UPDATE category SET deleted = true WHERE category_id = ?")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(nullable = false)
    private String name;
}
