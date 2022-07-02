package com.atifimal.task.superuploader2022.repository;

import com.atifimal.task.superuploader2022.entity.FileObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileObj, Long> {

}
