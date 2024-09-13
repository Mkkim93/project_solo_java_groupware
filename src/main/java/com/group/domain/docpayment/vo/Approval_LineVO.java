package com.group.domain.docpayment.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Approval_LineVO {

    private Integer id;
    private Integer approvalOrder;
    private LocalDateTime approvalStatus;
    private LocalDateTime approvalCreate;
    private Integer docPayId;
    private Integer empId;
}
