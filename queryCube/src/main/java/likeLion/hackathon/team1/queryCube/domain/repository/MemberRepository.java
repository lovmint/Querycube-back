package likeLion.hackathon.team1.queryCube.domain.repository;

        import likeLion.hackathon.team1.queryCube.domain.entity.Member;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 추가적인 쿼리 메서드나 기능을 정의할 수 있습니다.
}
