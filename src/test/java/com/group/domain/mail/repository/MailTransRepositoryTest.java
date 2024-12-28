package com.group.domain.mail.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MailTransRepositoryTest {

    @Autowired MailTransRepository mailTransRepository;

    @Test
    void mailBoxTrash() {
        Integer result = mailTransRepository.mailTransByTrash(1);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("체크박스로 선택된 mailBoxIds 를 벌크삭제")
    void bulkTrash() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);
    }

}