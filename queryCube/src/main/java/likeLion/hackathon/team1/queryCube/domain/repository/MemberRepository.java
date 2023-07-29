package likeLion.hackathon.team1.queryCube.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findByGoogleId(String googleId); // 구글 아이디로 멤버 조회
}


