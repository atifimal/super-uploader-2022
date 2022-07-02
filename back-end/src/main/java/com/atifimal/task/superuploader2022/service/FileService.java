package com.atifimal.task.superuploader2022.service;

import com.atifimal.task.superuploader2022.entity.FileObj;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    List<FileObj> getAll();
    FileObj getById(Long id);
    List<FileObj> saveAll(List<FileObj> files);
    FileObj save(MultipartFile mpFile);

}
