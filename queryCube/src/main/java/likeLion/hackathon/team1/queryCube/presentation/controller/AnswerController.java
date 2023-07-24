package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.service.AnswerService;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import likeLion.hackathon.team1.queryCube.presentation.response.AnswerInfoResponse;
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

    //유저별 답변 리스트
    @GetMapping("/answer-list-inMember/{member_id}")
    public ResponseEntity<List<AnswerInfoResponse>> getAllAnswerListInMember(@PathVariable Long member_id) {
        List<AnswerDto> roomDtoList = answerService.getAllAnswerListInMember(member_id);
        List<AnswerInfoResponse> response = roomDtoList.stream()
                .map(AnswerInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    //질문별 답변 리스트
    @GetMapping("/answer-list-inQuestion/{question_id}/{member_id}")
    public ResponseEntity<List<AnswerInfoResponse>> getAllAnswerListInQuestion(@PathVariable Long question_id, @PathVariable Long member_id) {
        System.out.println(member_id);
        List<AnswerDto> roomDtoList = answerService.getAllAnswerListInQuestion(question_id, member_id);
        List<AnswerInfoResponse> response = roomDtoList.stream()
                .map(AnswerInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{answer_id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long answer_id) {
        answerService.deleteAnswer(answer_id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{answer_id}")
    public ResponseEntity<Void> changeAnswerStatus (@RequestBody AddAnswerRequest request, @PathVariable Long answer_id) {
        Long updatedId = answerService.changeAnswerInfo(AnswerDto.from(request), answer_id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/like/{answer_id}/{member_id}")
    public ResponseEntity<Boolean> like(@PathVariable Long answer_id, @PathVariable Long member_id) {
        Boolean result = answerService.saveLike(answer_id, member_id);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }






}
