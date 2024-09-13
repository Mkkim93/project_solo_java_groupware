package com.group.domain.prod.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "prodimage")
public class ProdImage {

    public ProdImage() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "prodimg_name")
    private String prodImgName;

    @Column(name = "prodimg_url")
    private String prodImgUrl;

    @Column(name = "prodimg_createtime")
    private LocalDateTime prodImgCreateTime;

    @ManyToOne
    @JoinColumn(name = "prodId")
    private Prod prod;
}
