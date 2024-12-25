package com.group.domain.mailfile.entity;

import com.group.application.mail.dto.MailBoxDTO;
import com.group.domain.mail.entity.MailBox;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "mailfilestore")
@Getter
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class MailFileStore {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "mail_file_name")
    private String mailFileName;

    @Column(name ="mail_file_path")
    private String mailFilePath;

    @Column(name = "mail_file_size")
    private Long mailFileSize;

    @Column(name = "mail_file_type")
    private String mailFileType;

    @CreatedDate
    @Column(name = "mail_file_regdate")
    private LocalDateTime mailFileRegDate;

    @Column(name = "mail_file_originname")
    private String mailFileOriginName;

    @ManyToOne
    @JoinColumn(name = "mailbox_file_id")
    private MailBox mailBoxFileId;

    public void setFileData(MultipartFile file, String mailFileName, String mailFilePath, Integer mailBoxId) {
        this.mailFileOriginName = file.getOriginalFilename();
        this.mailFileName = mailFileName;
        this.mailFilePath = mailFilePath + mailFileName;
        this.mailFileSize = file.getSize();
        this.mailFileType = file.getContentType();
        this.mailBoxFileId = MailBox.builder()
                .id(mailBoxId)
                .build();
    }
}
