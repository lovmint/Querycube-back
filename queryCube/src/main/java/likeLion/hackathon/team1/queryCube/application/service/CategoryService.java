package likeLion.hackathon.team1.queryCube.application.service;


import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.Big3CategoryDto;
import likeLion.hackathon.team1.queryCube.application.dto.CategoryDto;
import likeLion.hackathon.team1.queryCube.domain.entity.*;
import likeLion.hackathon.team1.queryCube.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;
    private final CategoryInterestRepository categoryInterestRepository;
    private final Big3CategoryRepository big3CategoryInterestRepository;

    @Transactional
    public List<Big3CategoryDto> getCategoryList(){
        List<Big3Category> categoryList = big3CategoryInterestRepository.findAll();

        return categoryList.stream().map(Big3CategoryDto::from).collect(Collectors.toList());
    }

    public Boolean saveLike(Long category_id, Long member_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("no such room"));

        List<CategoryInterest> findCategoryInterest = categoryInterestRepository.findByLikerIdAndCategoryId(member, category);

        //System.out.println(findAnswerLike.isEmpty());
        if (findCategoryInterest.isEmpty()){

            CategoryInterest categoryInterest = CategoryInterest.toCategoryInterest(member, category);
            categoryInterestRepository.save(categoryInterest);

            return true;
        }else {

            categoryInterestRepository.deleteById(findCategoryInterest.get(0).getCategory_interest_id());
            //answerLikeRepository.deleteByLikerIdAndAnswerId(member, answer);
            //br.minusLike(boardId);
            return false;

        }

    }

    @Transactional
    public List<CategoryDto> getCategoryInterestListInMember(Long member_id){
        List<CategoryInterest> fundingList = categoryInterestRepository.findAll();
        List<Category> fundingListInRoom = new ArrayList<>();

        for(CategoryInterest f : fundingList){
            if(f.getLikerId().getMember_id() == member_id){
                fundingListInRoom.add(f.getCategoryId());
            }
        }

        return fundingListInRoom.stream().map(CategoryDto::from).collect(Collectors.toList());
    }
}
