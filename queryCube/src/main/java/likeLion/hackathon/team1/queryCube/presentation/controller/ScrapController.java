package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.ScrapFolderDto;
import likeLion.hackathon.team1.queryCube.application.service.AnswerService;
import likeLion.hackathon.team1.queryCube.application.service.ScrapService;
import likeLion.hackathon.team1.queryCube.presentation.request.AddAnswerRequest;
import likeLion.hackathon.team1.queryCube.presentation.request.AddScrapFolderRequest;
import likeLion.hackathon.team1.queryCube.presentation.response.AnswerInfoResponse;
import likeLion.hackathon.team1.queryCube.presentation.response.ScrapFolderInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/scrap")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ScrapController {

    @Autowired
    private final ScrapService scrapService;

    @PostMapping("/addScrapFolder/{member_id}")
    public ResponseEntity<Long> addScrapFolder(@RequestBody AddScrapFolderRequest request, @PathVariable Long member_id) {
        Long savedId = scrapService.addScrapFolder(ScrapFolderDto.toAddScrapFolder(request), member_id);
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/scrapFolder-list/{member_id}")
    public ResponseEntity<List<ScrapFolderInfoResponse>> getScrapFolderList(@PathVariable Long member_id) {
        List<ScrapFolderDto> scrapFolderDtoList = scrapService.getScrapFolderList(member_id);
        List<ScrapFolderInfoResponse> response = scrapFolderDtoList.stream()
                .map(ScrapFolderInfoResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteFolder/{scrap_folder_id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long scrap_folder_id) {
        scrapService.deleteScrapFolder(scrap_folder_id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/changeFolderName/{scrap_folder_id}")
    public ResponseEntity<Void> changeScrapFolderStatus (@RequestBody AddScrapFolderRequest request, @PathVariable Long scrap_folder_id) {
        Long updatedId = scrapService.changeScrapFolderInfo(ScrapFolderDto.from(request), scrap_folder_id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{scrap_folder_id}/{question_id}")
    public ResponseEntity<Boolean> scrapQuestion(@PathVariable Long scrap_folder_id, @PathVariable Long question_id) {
        Boolean result = scrapService.scrapQuestion(scrap_folder_id, question_id);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}

