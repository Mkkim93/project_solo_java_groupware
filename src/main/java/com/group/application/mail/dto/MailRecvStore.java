package com.group.application.mail.dto;

import com.group.domain.mail.entity.enums.ISCC;
import lombok.Data;

@Data
public class MailRecvStore {

    private Integer mailBoxId;
    private Integer empId;
    private ISCC iscc;

    public MailRecvStore(Integer mailBoxId, Integer empId) {
        this.mailBoxId = mailBoxId;
        this.empId = empId;
    }
}
