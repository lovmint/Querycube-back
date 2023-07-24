package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
