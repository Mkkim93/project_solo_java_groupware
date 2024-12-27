package com.group.domain.mailfile.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailFileStoreRepositoryTest {

    @Autowired
    MailFileStoreRepository mailFileStoreRepository;

    @Test
    void findByMailFileList() {
        Integer id = 45;
        mailFileStoreRepository.findByMailBoxId(id);
    }

}