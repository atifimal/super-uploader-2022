package com.atifimal.task.superuploader2022.controller;

import com.atifimal.task.superuploader2022.entity.FileObj;
import com.atifimal.task.superuploader2022.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileRestController {

    @Autowired
    FileService fileService;

    @Value("${front-end.url}")
    String frontEndUrl;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> getFile(@RequestParam("file") MultipartFile file) {
        fileService.save(file);
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .body("ok");
    }

    @GetMapping("/get")
    public ResponseEntity<List<FileObj>> getAll() {
        List<FileObj> getAll = fileService.getAll();
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(getAll);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Byte>> getOne(@PathVariable Long id) throws IOException {
        byte[] byteArr = fileService.getFileAsByteArr(id);
        List<Byte> byteList = new ArrayList<>();
        for (byte x : byteArr) byteList.add(x);
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "*")
                .body(byteList);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) throws IOException {
        fileService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header("Access-Control-Allow-Origin", "*")
                .header("Location", frontEndUrl)
                .body("deleted");
    }
}
