package com.group.domain.prod.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProdImageVO {

    private Integer id;
    private String prodImgName;
    private String prodImgUrl;
    private LocalDateTime prodImgCreateTime;
    private Integer prodId;
}
