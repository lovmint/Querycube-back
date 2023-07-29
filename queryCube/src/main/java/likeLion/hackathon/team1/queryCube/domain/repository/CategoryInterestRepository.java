package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryInterestRepository  extends JpaRepository<CategoryInterest, Long> {
    List<CategoryInterest> findByLikerIdAndCategoryId(Member liker_id, Category category_id);
}
