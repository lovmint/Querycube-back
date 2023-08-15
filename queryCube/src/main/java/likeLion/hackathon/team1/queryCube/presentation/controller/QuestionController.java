package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
import likeLion.hackathon.team1.queryCube.application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Get all questions
    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Get questions sorted by likes in descending order with user images
    @GetMapping("/sorted-by-likes")
    public List<QuestionDto> getQuestionsSortedByLikes() {
        return questionService.getQuestionsByLikesDescendingWithUserImages();
    }
}
