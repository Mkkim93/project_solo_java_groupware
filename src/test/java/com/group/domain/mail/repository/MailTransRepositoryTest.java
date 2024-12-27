package com.group.domain.mail.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailTransRepositoryTest {

    @Autowired MailTransRepository mailTransRepository;

    @Test
    void mailBoxTrash() {
        Integer result = mailTransRepository.mailTransByTrash(1);
        Assertions.assertThat(result).isEqualTo(1);
    }

}