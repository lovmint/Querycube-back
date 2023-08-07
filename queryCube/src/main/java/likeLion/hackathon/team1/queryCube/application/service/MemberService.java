package likeLion.hackathon.team1.queryCube.application.service;

        import likeLion.hackathon.team1.queryCube.application.dto.MemberInfoDto;
        import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
        import likeLion.hackathon.team1.queryCube.domain.entity.Member;
        import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberInfoDto getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            return new MemberInfoDto(
                    member.getMember_id(),
                    member.getName(),
                    member.getReward_point(),
                    member.getCreate_date()
            );
        }
        return null;
    }

    public List<QuestionDto> getMemberQuestions(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            return member.getMyQuestions();
        }
        return null;
    }

    public void googleLogin(Long memberId, String googleAccountId, String displayName) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            member.googleLogin(googleAccountId, displayName);
            memberRepository.save(member);
        }
    }

    public void logout(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member != null) {
            member.logout();
            memberRepository.save(member);
        }
    }
    // 다른 기능들을 추가할 수 있습니다.
}
