package likeLion.hackathon.team1.queryCube.application.service;

import likeLion.hackathon.team1.queryCube.application.dto.MemberDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long addMember(MemberDto dto) {
        // Check if the username is already taken
        if (memberRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Hash the password before storing it in the database
        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        Member newMember = Member.builder()
                .username(dto.getUsername())
                .password(hashedPassword)
                .build();
        memberRepository.save(newMember);
        return newMember.getMember_id();
    }

    public Optional<Member> findMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    // Remove the findMemberByGoogleId method as it is not needed for self-registration, login, and logout

    // Add a new method to check if a given username and password combination is valid for login
    public boolean isValidLogin(String username, String password) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            String hashedPassword = memberOptional.get().getPassword();
            return passwordEncoder.matches(password, hashedPassword);
        }
        return false;
    }

    @Transactional
    public void deleteMember(String username) {
        // Find the member by username
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with username: " + username));

        // Soft delete the member by marking it as deleted
        member.setDeleted(true);
        memberRepository.save(member);
    }

    //로그아웃 기능 추가
    public void logout(HttpSession session) {
        session.invalidate();
    }

    //구글 계정 연동 로그인 기능 추가
    @Transactional
    public boolean processGoogleLogin(String googleId) {
        // Check if a user with the given Google ID exists in the database
        Optional<Member> memberOptional = memberRepository.findByGoogleId(googleId);

        if (memberOptional.isPresent()) {
            // If the user exists, link the Google account to the existing user (Optional step)
            Member existingMember = memberOptional.get();
            // Perform any necessary updates or validations here
            // For example, update the last login timestamp or perform user verification

            return true;
        } else {
            // If the user does not exist, create a new user based on the Google account information
            Member newMember = Member.builder()
                    .googleId(googleId)
                    // Add other required fields here
                    .build();
            memberRepository.save(newMember);

            return true;
        }
    }
}
