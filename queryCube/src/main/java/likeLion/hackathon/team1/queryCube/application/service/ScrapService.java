package likeLion.hackathon.team1.queryCube.application.service;

import likeLion.hackathon.team1.queryCube.application.dto.AnswerDto;
import likeLion.hackathon.team1.queryCube.application.dto.ScrapFolderDto;
import likeLion.hackathon.team1.queryCube.domain.entity.*;
import likeLion.hackathon.team1.queryCube.domain.repository.*;
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
public class ScrapService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;
    private final ScrapFolderRepository scrapFolderRepository;

    @Transactional
    public Long addScrapFolder(ScrapFolderDto dto, Long member_id){

        //Category category = categoryRepository.findById(dto.getCategory_id()).orElseThrow(() -> new IllegalArgumentException("no such Category"));
        //Question question = questionRepository.findById(question_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("no such user"));


        ScrapFolder newScrapFolder = scrapFolderRepository.save(ScrapFolder.toScrapFolder(dto, member));
        return newScrapFolder.getScrap_folder_id();
    }

    @Transactional
    public List<ScrapFolderDto> getScrapFolderList(Long member_id){
        List<ScrapFolder> allScrapFolderList = scrapFolderRepository.findAll();
        List<ScrapFolder> scrapFolderList = new ArrayList<>();

        for(ScrapFolder f : allScrapFolderList){
            if(f.getMemberId().getMember_id() == member_id){
                scrapFolderList.add(f);
            }
        }

        return scrapFolderList.stream().map(ScrapFolderDto::from).collect(Collectors.toList());
    }

    @Transactional
    public void deleteScrapFolder(Long scrap_folder_id){
        //System.out.println("Id = " + scrap_folder_id);
        scrapFolderRepository.deleteById(scrap_folder_id);
    }


    public Long changeScrapFolderInfo(ScrapFolderDto dto, Long scrap_folder_id){
        ScrapFolder scrapFolder = scrapFolderRepository.findById(scrap_folder_id).orElseThrow(() -> new IllegalArgumentException("no such room"));
        scrapFolder.setScrap_folder_name(dto.getScrap_folder_name());


        ScrapFolder updatedScrapFolder = scrapFolderRepository.save(scrapFolder);
        return updatedScrapFolder.getScrap_folder_id();
    }
}
