package com.group.domain.docpayment.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "docpayfile")
public class DocPayFile {

    public DocPayFile() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "docfile_fname")
    private String docFileFName;

    @Column(name = "docfile_fpath")
    private String docFileFPath;

    @Column(name = "docfile_fsize")
    private Long docFileFSize;
}
