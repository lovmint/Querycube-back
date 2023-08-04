package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.ScrapFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapFolderRepository extends JpaRepository<ScrapFolder, Long> {
}
