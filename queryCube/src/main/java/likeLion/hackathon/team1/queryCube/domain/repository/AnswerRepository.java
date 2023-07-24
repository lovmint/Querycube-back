package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
