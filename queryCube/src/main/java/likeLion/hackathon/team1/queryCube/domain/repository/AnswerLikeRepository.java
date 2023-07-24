package likeLion.hackathon.team1.queryCube.domain.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.AnswerLike;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

public interface
AnswerLikeRepository extends JpaRepository<AnswerLike, Long>{

    List<AnswerLike> findByLikerIdAndAnswerId(Member liker_id, Answer answer_id);
    //void  deleteByLikerIdAndAnswerId(Member liker_id, Answer answer_id);
}
