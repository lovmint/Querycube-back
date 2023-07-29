package likeLion.hackathon.team1.queryCube.application.service;

import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Category;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.repository.CategoryRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.QuestionLikeRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionLikeRepository questionLikeRepository;

    public QuestionService(QuestionRepository questionRepository, MemberRepository memberRepository, CategoryRepository categoryRepository, QuestionLikeRepository questionLikeRepository) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.questionLikeRepository = questionLikeRepository;
    }

    @Transactional
    public Long addQuestion(QuestionDto dto, Long questioner_member_id, Long category_id) {
        Member questioner = memberRepository.findById(questioner_member_id)
                .orElseThrow(() -> new IllegalArgumentException("No such questioner member"));
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new IllegalArgumentException("No such category"));

        Question newQuestion = Question.toQuestion(dto.getQuestion_sentence(), questioner, category);
        questionRepository.save(newQuestion);
        return newQuestion.getQuestion_id();
    }

    @Transactional
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteQuestion(Long question_id) {
        questionRepository.deleteById(question_id);
    }

    @Transactional
    public Long changeQuestionInfo(QuestionDto dto, Long question_id) {
        Question question = questionRepository.findById(question_id)
                .orElseThrow(() -> new IllegalArgumentException("No such question"));
        question.setQuestion_sentence(dto.getQuestion_sentence());
        question.setIsNotification_status(dto.getIsNotification_status());

        Question updatedQuestion = questionRepository.save(question);
        return updatedQuestion.getQuestion_id();
    }

    public Boolean saveLike(Long question_id, Long member_id) {
        Question question = questionRepository.findById(question_id)
                .orElseThrow(() -> new IllegalArgumentException("No such question"));
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new IllegalArgumentException("No such member"));

        List<QuestionLike> findQuestionLike = questionLikeRepository.findByLikerIdAndQuestionId(member, question);

        if (findQuestionLike.isEmpty()) {
            QuestionLike questionLike = QuestionLike.toQuestionLike(member, question);
            questionLikeRepository.save(questionLike);
            return true;
        } else {
            questionLikeRepository.deleteById(findQuestionLike.get(0).getQuestion_like_id());
            return false;
        }
    }

    public boolean findQuestionLike(Long question_id, Long member_id) {
        Question question = questionRepository.findById(question_id)
                .orElseThrow(() -> new IllegalArgumentException("No such question"));
        Member member = memberRepository.findById(member_id)
                .orElseThrow(() -> new IllegalArgumentException("No such member"));

        List<QuestionLike> findQuestionLike = questionLikeRepository.findByLikerIdAndQuestionId(member, question);

        return !findQuestionLike.isEmpty();
    }
}
