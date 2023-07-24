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
@SQLDelete(sql = "UPDATE region SET deleted = true WHERE region_id = ?")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_id;

    @Column(nullable = false)
    private String name;
}
