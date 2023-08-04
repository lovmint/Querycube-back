package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapQuestionRepository extends JpaRepository<ScrapQuestion, Long> {

    List<ScrapQuestion> findByScrapFolderIdAndQuestionId(ScrapFolder scrap_folder_id, Question question_id);
}
