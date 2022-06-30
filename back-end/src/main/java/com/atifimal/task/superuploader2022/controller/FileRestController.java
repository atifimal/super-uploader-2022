package com.atifimal.task.superuploader2022.controller;

import com.atifimal.task.superuploader2022.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileRestController {

    @Autowired
    FileService fileService;
}
