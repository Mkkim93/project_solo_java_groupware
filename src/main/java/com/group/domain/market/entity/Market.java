package com.group.domain.market.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "market")
public class Market {

    public Market() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "market_title")
    private String marketTitle;

    @Column(name = "market_content")
    private String marketContent;

    @Column(name = "market_mileage")
    private Integer marketMileage;

    @Column(name = "market_quantity")
    private Integer marketQuantity;

    @Column(name = "market_createdate")
    private LocalDateTime marketCreateDate;

    @Column(name = "market_updatedate")
    private LocalDateTime marketUpdateDate;

    // 양방향 mapping
    @OneToMany(mappedBy = "market")
    Set<Market_Employee> marketEmployee;
}
