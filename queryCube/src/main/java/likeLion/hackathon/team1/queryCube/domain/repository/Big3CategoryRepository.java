package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Big3Category;
import likeLion.hackathon.team1.queryCube.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Big3CategoryRepository extends JpaRepository<Big3Category, Long> {
}
