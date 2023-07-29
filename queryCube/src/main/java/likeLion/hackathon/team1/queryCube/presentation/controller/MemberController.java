package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.GoogleLoginDto;
import likeLion.hackathon.team1.queryCube.application.service.MemberService;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 구글 로그인 기능을 위한 엔드포인트
    @PostMapping("/login/google")
    public Member loginWithGoogle(@RequestBody GoogleLoginDto googleLoginDto) {
        String googleId = googleLoginDto.getGoogleId();

        // 구글 아이디로 회원을 찾아봅니다.
        Member member = memberService.findMemberByGoogleId(googleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with Google ID: " + googleId));

        // 구글 로그인 성공 시에 필요한 처리를 추가합니다.
        // (예: JWT 토큰 발급 등)

        return member;
    }
}
