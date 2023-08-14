package likeLion.hackathon.team1.queryCube.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class ConcreteCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long concrete_category_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category_id;

    @Column(nullable = false)
    private String name;

}
