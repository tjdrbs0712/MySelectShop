package com.sparta.myselectshop.Service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    //폴더 추가
    public void addFolders(List<String> folderNames, User user) {

        // 이미 존재하는 폴더인지 확인 findAllByUserAndNameIn 해당 유저의 폴더를 모두 가져옴
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        //iter 단축 명령어
        for (String folderName : folderNames) {
            if(!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            }
            else{
                throw new IllegalArgumentException("이미 존재하는 폴더명입니다.");
            }
        }

        folderRepository.saveAll(folderList);

    }

    //폴더 조회
    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtosList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtosList.add(new FolderResponseDto(folder));
        }
        return responseDtosList;
    }

    //폴더명 중복 확인
    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }
}
