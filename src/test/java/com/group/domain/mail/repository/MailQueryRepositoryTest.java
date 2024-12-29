package com.group.domain.mail.repository;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.mail.entity.MailBox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailQueryRepositoryTest {

    @Autowired MailQueryRepository mailQueryRepository;

    @Test
    @DisplayName("참조자 조회")
    void findCC() {
        mailQueryRepository.findByMailReceiverCC(25);
    }
}