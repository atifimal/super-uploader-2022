package com.atifimal.task.superuploader2022.controller;

import com.atifimal.task.superuploader2022.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class FileRestController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload-file")
    public void getFile(@RequestParam("file") MultipartFile file) {
        fileService.save(file);
    }
}
