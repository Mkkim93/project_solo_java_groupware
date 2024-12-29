package com.group.domain.mail.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailTransQueryRepositoryTest {

    @Autowired MailTransQueryRepository mqry;

    @Test
    @DisplayName("체크박스에 해당된 데이터 bulk update")
    void bulkTest() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(5);

        mqry.bulkTrashUpdate(list);

    }

    @Test
    @DisplayName("메일의 참조자 조회")
    void findCC() {

    }

}