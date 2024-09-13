package com.group.domain.market.vo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MarketVO {

    private Integer id;
    private String marketTitle;
    private String marketContent;
    private Integer marketMileage;
    private Integer marketQuantity;
    private LocalDateTime marketCreateDate;
    private LocalDateTime marketUpdateDate;
}
