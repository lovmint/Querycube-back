package likeLion.hackathon.team1.queryCube.application.service;

import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator; // Add the import statement for Comparator
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Retrieve the full list of questions
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Convert Question entity to QuestionDto
    private QuestionDto convertToDto(Question question) {
        return new QuestionDto(
                question.getQuestion_id(),
                question.getQuestion_title(),
                question.getQuestion_content()
                // Add other fields as needed
        );
    }

    // Get questions sorted by the number of likes in descending order
    public List<QuestionDto> getQuestionsByLikesDescending() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .sorted(Comparator.comparingInt(question -> -question.getQuestionLikes().size()))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
