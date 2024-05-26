package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;

    //폴더 추가
    @PostMapping("/folder")
    public void addFolders(@RequestBody FolderRequestDto folderRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        List<String> folderNames = folderRequestDto.getFolderNames();
        System.out.println("!@#!@#!@#!@#!@#!@#!@#!@#!@\n\n\n\n\n\n@#!@#!@#!@#!@#!@#!@#!@#");
        folderService.addFolders(folderNames, userDetails.getUser());
    }

}
