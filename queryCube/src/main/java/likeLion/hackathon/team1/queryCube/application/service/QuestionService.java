package likeLion.hackathon.team1.queryCube.application.service;

import likeLion.hackathon.team1.queryCube.application.dto.QuestionDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.repository.QuestionRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, MemberRepository memberRepository) {
        this.questionRepository = questionRepository;
        this.memberRepository = memberRepository;
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
        int questionLikeCount = question.getQuestionLikes().size();
        return new QuestionDto(
                question.getQuestion_id(),
                question.getQuestion_title(),
                question.getQuestion_content(),
                question.getQuestioner_id().getMember_id(),
                question.getCreate_date(),
                questionLikeCount,
                question.getImageUrls(),
                null // Set userImage as null initially
        );
    }

    // Get questions sorted by the number of likes in descending order
    public List<QuestionDto> getQuestionsByLikesDescendingWithUserImages() {
        List<Question> questions = questionRepository.findAll();
        Map<Long, String> userImages = getUserImagesMap(questions);

        return questions.stream()
                .sorted(Comparator.comparingInt(question -> -question.getQuestionLikes().size()))
                .map(question -> convertToDtoWithUserImage(question, userImages))
                .collect(Collectors.toList());
    }

    // Convert Question entity to QuestionDto with user image
    private QuestionDto convertToDtoWithUserImage(Question question, Map<Long, String> userImages) {
        int questionLikeCount = question.getQuestionLikes().size();
        return new QuestionDto(
                question.getQuestion_id(),
                question.getQuestion_title(),
                question.getQuestion_content(),
                question.getQuestioner_id().getMember_id(),
                question.getCreate_date(),
                questionLikeCount,
                question.getImageUrls(),
                userImages.getOrDefault(question.getQuestioner_id().getMember_id(), null)
        );
    }

    // Retrieve user images and create a map of member IDs and their images
    private Map<Long, String> getUserImagesMap(List<Question> questions) {
        List<Long> userIds = questions.stream()
                .map(question -> question.getQuestioner_id().getMember_id())
                .distinct()
                .collect(Collectors.toList());

        List<Member> users = memberRepository.findAllById(userIds);
        return users.stream()
                .collect(Collectors.toMap(Member::getMember_id, Member::getImageUrl));
    }
}
