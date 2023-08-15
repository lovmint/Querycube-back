package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.CategoryDto;
import likeLion.hackathon.team1.queryCube.application.service.AnswerService;
import likeLion.hackathon.team1.queryCube.application.service.CategoryService;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import likeLion.hackathon.team1.queryCube.presentation.response.AnswerInfoResponse;
import likeLion.hackathon.team1.queryCube.presentation.response.CategoryInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping("/category-list")
    public ResponseEntity<List<CategoryInfoResponse>> getCategoryList() {
        List<CategoryDto> categoryDtoList = categoryService.getCategoryList();
        List<CategoryInfoResponse> response = categoryDtoList.stream()
                .map(CategoryInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/like/{category_id}/{member_id}")
    public ResponseEntity<Boolean> like(@PathVariable Long category_id, @PathVariable Long member_id) {
        Boolean result = categoryService.saveLike(category_id, member_id);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/category-list/{member_id}")
    public ResponseEntity<List<CategoryInfoResponse>> getCategoryInterestListInMember(@PathVariable Long member_id) {
        List<CategoryDto> categoryDtoList = categoryService.getCategoryInterestListInMember(member_id);
        List<CategoryInfoResponse> response = categoryDtoList.stream()
                .map(CategoryInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


}
