package com.group.domain.mailfile.repository;

import com.group.application.mailfile.dto.MailFileDTO;
import com.group.domain.mailfile.entity.MailFileStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MailFileStoreRepository extends JpaRepository<MailFileStore, Integer> {

    @Query("select new com.group.application.mailfile.dto.MailFileDTO(" +
            " mf.id, mf.mailFileName, mf.mailFileSize, mf.mailFileRegDate, " +
            " mf.mailFileOriginName, mf.mailFilePath, mf.mailBoxFileId.id, " +
            " mf.mailFileType) " +
            " from MailFileStore mf , MailBox mb " +
            " where mf.mailBoxFileId.id = mb.id and mb.id = :id")
    List<MailFileDTO> findByMailBoxId(@Param("id") Integer id);
}
