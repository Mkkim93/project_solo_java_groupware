package com.group.application.file;

import com.group.domain.file.entity.FileStore;
import com.group.domain.file.repository.FileStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class FileStoreServiceTest {

    @Autowired
    FileStoreRepository fileStoreRepository;

    @Autowired
    FileStoreService fileStoreService;

    @Test
    public void findByIdFileStore() {
        Integer storeId = 20;
//        List<Object[]> byFileBoardStoreId = fileStoreRepository.findByFileBoardStoreId(storeId);
        List<FileBoardStoreDTO> byFileBoardStoreId = fileStoreRepository.findByFileBoardStoreId(storeId);
        for (FileBoardStoreDTO fileBoardStoreDTO : byFileBoardStoreId) {
            System.out.println("fileBoardStoreDTO.getFileName() = " + fileBoardStoreDTO.getFileName());
            System.out.println("fileBoardStoreDTO.getFilePath() = " + fileBoardStoreDTO.getFilePath());
            System.out.println("fileBoardStoreDTO.getFileSize() = " + fileBoardStoreDTO.getFileSize());
            System.out.println("fileBoardStoreDTO.getFileRegDate() = " + fileBoardStoreDTO.getFileRegDate());
            System.out.println("fileBoardStoreDTO.getId() = " + fileBoardStoreDTO.getId());
            System.out.println("fileBoardStoreDTO.getFileBoardId() = " + fileBoardStoreDTO.getFileBoardId());
        }

    }

    @Test
    public void findById() {
        Integer id = 54;
        FileStore fileStore = fileStoreRepository.findById(id).get();
        System.out.println("fileStore = " + fileStore.getFileName());
        System.out.println("fileStore.getFilePath() = " + fileStore.getFilePath());
        System.out.println("fileStore.getId() = " + fileStore.getId());
        System.out.println("fileStore.getFileBoardId() = " + fileStore.getFileBoardId());
    }

    @Test
    public void findByStoreId() {
        Integer id = 58;
        Integer boardDtoId = 69;
        FileStore onlyId = fileStoreService.findById(boardDtoId);
        System.out.println("onlyId = " + onlyId.getId());
        System.out.println("onlyId.getFilePath() = " + onlyId.getFilePath());
        System.out.println("onlyId.getFileName() = " + onlyId.getFileName());
        System.out.println("onlyId.getOriginFileName() = " + onlyId.getOriginFileName());

        String fullPath = fileStoreService.getFullPath(onlyId.getOriginFileName());
        System.out.println("fullPath = " + fullPath);

        List<FileBoardStoreDTO> storedId = fileStoreService.findByStoreId(id);

        for (FileBoardStoreDTO stoId : storedId) {
            System.out.println("stoId.getId() = " + stoId.getId());
            System.out.println("stoId.getFilePath() = " + stoId.getFilePath());
        }
    }
}