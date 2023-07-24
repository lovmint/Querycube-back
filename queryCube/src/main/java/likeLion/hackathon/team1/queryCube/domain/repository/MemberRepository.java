package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
