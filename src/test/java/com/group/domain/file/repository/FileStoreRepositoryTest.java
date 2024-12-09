package com.group.domain.file.repository;

import com.group.application.file.FileBoardStoreDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileStoreRepositoryTest {

    @Autowired FileStoreRepository fileStoreRepository;

    @Test
    void fileSearchTestV1() {
        List<FileBoardStoreDTO> result = fileStoreRepository.findByFileBoardStoreId(166);
        assertThat(result).isNotNull();
        for (FileBoardStoreDTO dto : result) {
            System.out.println("dto.getFileStoreId() = " + dto.getId());
            System.out.println("dto.getFileBoardId() = " + dto.getFileBoardId());
            System.out.println("dto.getFileName() = " + dto.getFileName());
        }
    }

    @Test
    void fileSearchTestV2() {
        List<FileBoardStoreDTO> result = fileStoreRepository.findByFileBoardStoreId(81);
        assertThat(result).isNotNull();
        for (FileBoardStoreDTO dto : result) {
            System.out.println("dto.getFileStoreId() = " + dto.getId());
            System.out.println("dto.getFileBoardId() = " + dto.getFileBoardId());
            System.out.println("dto.getFileName() = " + dto.getFileName());
        }
    }

}