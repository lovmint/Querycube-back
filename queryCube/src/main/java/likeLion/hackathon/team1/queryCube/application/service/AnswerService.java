package likeLion.hackathon.team1.queryCube.application.service;



import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.domain.entity.Answer;
import likeLion.hackathon.team1.queryCube.domain.entity.AnswerLike;
import likeLion.hackathon.team1.queryCube.domain.entity.Member;
import likeLion.hackathon.team1.queryCube.domain.entity.Question;
import likeLion.hackathon.team1.queryCube.domain.repository.AnswerLikeRepository;
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
    private final AnswerLikeRepository answerLikeRepository;

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
    public List<AnswerDto> getAllAnswerListInQuestion(Long question_id, Long member_id){
        List<Answer> fundingList = answerRepository.findAll();
        //List<Answer> fundingListInRoom = new ArrayList<>();
        List<AnswerDto> fundingDtoListInRoom = new ArrayList<>();

        System.out.println(member_id);

        for(Answer f : fundingList){
            if(f.getQuestion_id().getQuestion_id() == question_id){
                //fundingListInRoom.add(f);
                AnswerDto dto = AnswerDto.from(f);
                dto.setIsLike_active(findAnswerLike(dto.getAnswer_id(), member_id));
                fundingDtoListInRoom.add(dto);
            }
        }




        return fundingDtoListInRoom;
    }

    @Transactional
    public void deleteAnswer(Long answer_Id){
        System.out.println("roomId = " + answer_Id);
        answerRepository.deleteById(answer_Id);
    }


    public Long changeAnswerInfo(AnswerDto dto, Long answer_id){
        Answer answer = answerRepository.findById(answer_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        answer.setAnswer_sentence(dto.getAnswer_sentence());


        Answer updatedAnswer = answerRepository.save(answer);
        return updatedAnswer.getAnswer_id();
    }

    public Boolean saveLike(Long answer_id, Long member_id) {
        Answer answer = answerRepository.findById(answer_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("no such room"));

        List<AnswerLike> findAnswerLike = answerLikeRepository.findByLikerIdAndAnswerId(member, answer);

        //System.out.println(findAnswerLike.isEmpty());
        if (findAnswerLike.isEmpty()){

            AnswerLike answerLike = AnswerLike.toAnswerLike(member, answer);
            //이 답글에 좋아요 누를 사람이 답글의 질문의 질문자인지 확인.
            if(answer.getQuestion_id().getQuestioner_id().getMember_id() == member_id){
                answer.setIsQuestioner_like(true);
                answerRepository.save(answer);
            }
            answer.setAnswer_like_num(answer.getAnswer_like_num()+1);
            answerRepository.save(answer);
            answerLikeRepository.save(answerLike);
            //br.plusLike(boardId);
            return true;
        }else {
            //System.out.println(findAnswerLike.size());
            //이 답글에 좋아요 누를 사람이 답글의 질문의 질문자인지 확인.
            if(answer.getQuestion_id().getQuestioner_id().getMember_id() == member_id){
                answer.setIsQuestioner_like(false);
                answerRepository.save(answer);
            }
            answer.setAnswer_like_num(answer.getAnswer_like_num()-1);
            answerRepository.save(answer);
            answerLikeRepository.deleteById(findAnswerLike.get(0).getAnswer_like_id());
            //answerLikeRepository.deleteByLikerIdAndAnswerId(member, answer);
            //br.minusLike(boardId);
            return false;

        }

    }

    public boolean findAnswerLike(Long answer_id, Long member_id) {
        // 저장된 DTO 가 없다면 0, 있다면 1
        Answer answer = answerRepository.findById(answer_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("no such room"));

        List<AnswerLike> findAnswerLike = answerLikeRepository.findByLikerIdAndAnswerId(member, answer);


        if (findAnswerLike.isEmpty()){
            return false;
        }else {

            return true;
        }
    }

}
