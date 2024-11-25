package com.group.domain.mail.entity;

import com.group.domain.hr.entity.Employee;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailfile")
@Getter
public class MailFile {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "filename")
    private String FileName;

    @Column(name = "filepath")
    private String FilePath;

    @Column(name = "filesize")
    private Long fileSize;

    @ManyToMany(fetch = LAZY, mappedBy = "mailFiles")
    private List<MailBox> mailBox = new ArrayList<>();
}
