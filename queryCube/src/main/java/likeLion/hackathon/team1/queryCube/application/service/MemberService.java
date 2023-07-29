import likeLion.hackathon.team1.queryCube.application.dto.MemberDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long addMember(MemberDto dto) {
        Member newMember = Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
        memberRepository.save(newMember);
        return newMember.getMember_id();
    }

    public Optional<Member> findMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findMemberByGoogleId(String googleId) {
        return memberRepository.findByGoogleId(googleId);
    }
}
