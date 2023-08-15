package likeLion.hackathon.team1.queryCube.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likeLion.hackathon.team1.queryCube.domain.entity.Big3Category;
import likeLion.hackathon.team1.queryCube.domain.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Big3CategoryDto {

    private Long big3_category_id;

    private String name;
    private List<Category> categoryList = new ArrayList<>();

    public static Big3CategoryDto from(Big3Category big3Category) {
        return Big3CategoryDto.builder()
                .big3_category_id(big3Category.getBig3_category_id())
                .name(big3Category.getName())
                .categoryList(big3Category.getCategoryList())
                .build();
    }
}
