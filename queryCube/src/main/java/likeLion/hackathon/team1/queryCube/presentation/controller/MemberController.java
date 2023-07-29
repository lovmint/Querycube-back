package likeLion.hackathon.team1.queryCube.presentation.controller;

import likeLion.hackathon.team1.queryCube.application.dto.MemberDto;
import likeLion.hackathon.team1.queryCube.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 기능 추가를 위한 엔드포인트
    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody MemberDto memberDto) {
        Long memberId = memberService.addMember(memberDto);
        return ResponseEntity.ok(memberId);
    }

    // 로그인 기능을 위한 엔드포인트
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        boolean isValidLogin = memberService.isValidLogin(username, password);

        if (isValidLogin) {
            // 로그인 성공 시에 필요한 처리를 추가합니다.
            return ResponseEntity.ok("Login successful");
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }



    // 회원탈퇴 기능 추가를 위한 엔드포인트
    @PostMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestBody MemberDto memberDto) {
        String username = memberDto.getUsername();

        // 회원탈퇴 서비스를 호출하여 해당 유저를 삭제합니다.
        memberService.deleteMember(username);

        // 회원탈퇴 성공 시에 200 OK 응답을 반환합니다.
        return ResponseEntity.ok("Member deleted successfully");
    }

    // 로그아웃 기능 추가를 위한 엔드포인트
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // 로그아웃 처리를 수행합니다.
        memberService.logout(session);

        // 로그아웃 성공 시에 200 OK 응답을 반환합니다.
        return ResponseEntity.ok("Logout successful");
    }

    @Configuration
    public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(); // You can use any implementation of PasswordEncoder you prefer
        }
    }

}