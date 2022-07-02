package com.atifimal.task.superuploader2022.service;

import com.atifimal.task.superuploader2022.entity.FileObj;
import com.atifimal.task.superuploader2022.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Component
public class FileService_Impl implements FileService {

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @Autowired
    FileRepository fileRepository;

    @Override
    public List<FileObj> getAll() {
        return fileRepository.findAll();
    }

    @Override
    public FileObj getById(Long id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public List<FileObj> saveAll(List<FileObj> files) {
        return fileRepository.saveAll(files);
    }

    @Override
    public FileObj save(MultipartFile mpFile) {
        FileObj file = new FileObj(path.concat(Objects.requireNonNull(mpFile.getOriginalFilename())), mpFile.getSize(), mpFile.getOriginalFilename(), mpFile.getContentType());
        Path root = Paths.get(path);
        try {
            Files.copy(mpFile.getInputStream(), root.resolve(Objects.requireNonNull(mpFile.getOriginalFilename())));
            mpFile.getInputStream().close();
        } catch (Exception e) {
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage());
        }
        return fileRepository.save(file);
    }

}
