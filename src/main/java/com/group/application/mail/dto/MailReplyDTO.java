package com.group.application.mail.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MailReplyDTO {

    private Integer mailBoxId; // 원본 메일 id
    private Integer mailParentId; // 원본 메일을 참조할 자식 컬럼
    private String mailTitle; // 답장 제목
    private String mailContent; // 답장 내용
    private LocalDateTime mailDate; // 답장 날짜
    private Integer senderEmpId; // 답장 메일 작성자
    private String mailDeleted;
    private String mailStatus;


}
