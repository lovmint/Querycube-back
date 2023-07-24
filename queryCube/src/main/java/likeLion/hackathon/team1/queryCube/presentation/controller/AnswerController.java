package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.service.AnswerService;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AnswerController {

    @Autowired
    private final AnswerService answerService;


    @PostMapping("/{question_id}/{answerer_member_id}")
    public ResponseEntity<Long> save(@RequestBody AddAnswerRequest request, @PathVariable Long question_id, @PathVariable Long answerer_member_id) {
        Long savedId = answerService.addAnswer(AnswerDto.toAdd(request), question_id, answerer_member_id);
        return ResponseEntity.ok(savedId);
    }
}
