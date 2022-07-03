package com.atifimal.task.superuploader2022.controller;

import com.atifimal.task.superuploader2022.entity.FileObj;
import com.atifimal.task.superuploader2022.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileRestController {

    @Autowired
    FileService fileService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> getFile(@RequestParam("file") MultipartFile file) {
        fileService.save(file);
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .body("ok");
    }

    @GetMapping("/get")
    public List<FileObj> getAll() {
        return fileService.getAll();
    }
}
