package likeLion.hackathon.team1.queryCube.application.dto;

import likeLion.hackathon.team1.queryCube.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long category_id;

    private String name;


    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .category_id(category.getCategory_id())
                .name(category.getName())
                .build();
    }

}
