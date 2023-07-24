package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
