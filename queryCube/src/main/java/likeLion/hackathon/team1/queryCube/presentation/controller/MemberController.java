package likeLion.hackathon.team1.queryCube.presentation.controller;


import likeLion.hackathon.team1.queryCube.application.dto.MemberInfoDto;
import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
import likeLion.hackathon.team1.queryCube.application.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{memberId}/info")
    public MemberInfoDto getMemberInfo(@PathVariable Long memberId) {
        return memberService.getMemberInfo(memberId);
    }

    @GetMapping("/{memberId}/questions")
    public List<QuestionDto> getMemberQuestions(@PathVariable Long memberId) {
        return memberService.getMemberQuestions(memberId);
    }

    @PostMapping("/{memberId}/google-login")
    public ResponseEntity<String> googleLogin(@PathVariable Long memberId, @RequestBody GoogleLoginRequest request) {
        memberService.googleLogin(memberId, request.getGoogleAccountId(), request.getDisplayName(), request.getCode());
        return ResponseEntity.ok("구글 계정 연동 완료");
    }

    @PostMapping("/{memberId}/logout")
    public ResponseEntity<String> logout(@PathVariable Long memberId) {
        memberService.logout(memberId);
        return ResponseEntity.ok("로그아웃 완료");
    }

    // 다른 엔드포인트와 기능을 추가할 수 있습니다.

    @Data
    @Builder
    public static class GoogleLoginRequest {
        private String googleAccountId;
        private String displayName;
        private String code;
    }
}
