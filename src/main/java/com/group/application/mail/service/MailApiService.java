package com.group.application.mail.service;

import com.group.domain.mail.repository.MailRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailApiService {

    private final MailRepositoryImpl mailRepositoryImpl;

}
