package com.app.oauth.controller;

import com.app.oauth.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileDisplayController {

    private final FileService fileService;

//    ↓ 쿼리스트링
    @GetMapping("/display")
    @ResponseBody
    public byte[] display(String fileName) {
        return fileService.getDisplayPath(fileName);
    }
}
