package likeLion.hackathon.team1.queryCube.domain.repository;

import likeLion.hackathon.team1.queryCube.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
