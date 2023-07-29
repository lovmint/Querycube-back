package likeLion.hackathon.team1.queryCube.presentation.response;

import likeLion.hackathon.team1.queryCube.application.dto.CategoryDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryInfoResponse {


    private Long category_id;
    private String name;

    public static CategoryInfoResponse from(CategoryDto categoryDto) {
        return CategoryInfoResponse.builder()
                .category_id(categoryDto.getCategory_id())
                .name(categoryDto.getName())
                .build();
    }



}
