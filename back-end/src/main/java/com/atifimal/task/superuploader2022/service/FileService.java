package com.atifimal.task.superuploader2022.service;

import com.atifimal.task.superuploader2022.entity.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FileService {
    List<File> getAll();
    File getById(Long id);
    List<File> saveAll(List<File> files);
    File save(File file);

}
