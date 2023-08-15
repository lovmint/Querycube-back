package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE big3_category SET deleted = true WHERE Big3_category_id = ?")
public class Big3Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long big3_category_id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "big3_category_id", cascade = CascadeType.ALL)
    private List<Category> categoryList = new ArrayList<>();


}
