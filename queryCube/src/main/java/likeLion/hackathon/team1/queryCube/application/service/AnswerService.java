package likeLion.hackathon.team1.queryCube.application.service;



import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.repository.AnswerRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.MemberRepository;
import likeLion.hackathon.team1.queryCube.domain.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public Long addAnswer(AnswerDto dto, Long question_id, Long answerer_member_id){

        //Category category = categoryRepository.findById(dto.getCategory_id()).orElseThrow(() -> new IllegalArgumentException("no such Category"));
        Question question = questionRepository.findById(question_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Member member = memberRepository.findById(answerer_member_id).orElseThrow(() -> new IllegalArgumentException("no such user"));


        Answer newAnswer = answerRepository.save(Answer.toAnswer(dto, question, member));
        return newAnswer.getAnswer_id();
    }

    @Transactional
    public List<AnswerDto> getAllAnswerListInMember(Long member_id){
        List<Answer> fundingList = answerRepository.findAll();
        List<Answer> fundingListInRoom = new ArrayList<>();

        for(Answer f : fundingList){
            if(f.getAnswerer_id().getMember_id() == member_id){
                fundingListInRoom.add(f);
            }
        }

        return fundingListInRoom.stream().map(AnswerDto::from).collect(Collectors.toList());
    }

    @Transactional
    public List<AnswerDto> getAllAnswerListInQuestion(Long question_id){
        List<Answer> fundingList = answerRepository.findAll();
        List<Answer> fundingListInRoom = new ArrayList<>();

        for(Answer f : fundingList){
            if(f.getQuestion_id().getQuestion_id() == question_id){
                fundingListInRoom.add(f);
            }
        }

        return fundingListInRoom.stream().map(AnswerDto::from).collect(Collectors.toList());
    }


}
