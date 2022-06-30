package com.atifimal.task.superuploader2022.service;

import com.atifimal.task.superuploader2022.entity.File;
import com.atifimal.task.superuploader2022.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FileService_Impl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<File> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public File getById(Long id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public List<File> saveAll(List<File> files) {
        return fileRepository.saveAll(files);
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

}
