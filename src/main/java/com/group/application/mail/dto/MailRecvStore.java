package com.group.application.mail.dto;

import lombok.Data;

@Data
public class MailRecvStore {

    private Integer mailBoxId;
    private Integer empId;

    public MailRecvStore(Integer mailBoxId, Integer empId) {
        this.mailBoxId = mailBoxId;
        this.empId = empId;
    }
}
