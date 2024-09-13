package com.group.domain.docpayment.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocPaymentVO {

    private Integer id;
    private String docPayNo;
    private String docPayType;
    private String docPayTitle;
    private String docPayContent;
    private LocalDateTime docPayCreate;
    private LocalDateTime docPayUpdate;
    private Boolean docPayStatus;
    private Integer empId;
    private Integer docFileId;
    private Integer approvalId;
}
