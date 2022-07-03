package com.atifimal.task.superuploader2022.service;

import com.atifimal.task.superuploader2022.entity.FileObj;
import com.atifimal.task.superuploader2022.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
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
        String fileName = fileNameHandler(Objects.requireNonNull(mpFile.getOriginalFilename()));
        FileObj file = new FileObj(
                path.concat(fileName),
                mpFile.getSize(),
                fileName,
                mpFile.getContentType());
        try {
            Files.copy(mpFile.getInputStream(), Paths.get(path).resolve(Objects.requireNonNull(fileName)));
        } catch (Exception e) {
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage());
        }
        return fileRepository.save(file);
    }

    public String fileNameHandler(String fileName) { // If a file exist with the same name, add postfix
        if (Files.exists(Paths.get(path.concat(fileName)))) {
            long i = 1;
            String[] str = fileName.split("\\.");
            if (str.length > 1) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < str.length - 1; j++) sb.append(str[j]);
                String str2 = sb.toString();
                String str3 = str[str.length - 1];
                while (Files.exists(Paths.get(path.concat(str2).concat("(").concat(Long.toString(i).concat(").").concat(str3)))))
                    i++;
                fileName = str2.concat("(").concat(Long.toString(i).concat(").").concat(str3));
            }
        }
        return fileName;
    }
}
