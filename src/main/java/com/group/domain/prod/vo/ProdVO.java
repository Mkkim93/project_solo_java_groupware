package com.group.domain.prod.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProdVO {

    private Integer id;
    private String prodTitle;
    private String prodContent;
    private Integer prodPrice;
    private String prodCategory;
    private LocalDateTime prodCreate;
    private LocalDateTime prodUpdate;
    private Integer empId;
}
