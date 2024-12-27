package com.group.application.mailfile.service;

import com.group.application.file.FileStoreService;
import com.group.application.mail.dto.MailBoxDTO;
import com.group.application.mailfile.dto.MailFileDTO;
import com.group.domain.mailfile.entity.MailFileStore;
import com.group.domain.mailfile.repository.MailFileStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MailFileStoreService {

    @Value("${mailfile.dir}")
    private String mailFileDir;

    private final MailFileStoreRepository mailFileStoreRepository;

    public List<MailFileDTO> save(Integer mailBoxId, List<MultipartFile> mailFiles) throws IOException {
        List<MailFileStore> mailFileStores = new ArrayList<>();

        boolean existFile = mailFiles.stream().allMatch(MultipartFile::isEmpty);
        for (MultipartFile mailFile : mailFiles) {

            if (existFile) {
                continue;
            }
            String mailFilePath = mailFileDir;
            UUID uuid = UUID.randomUUID();
            String mailFileName = uuid + "_" + mailFile.getOriginalFilename();
            File saveFile = new File(mailFilePath, mailFileName);

            mailFile.transferTo(saveFile);

            MailFileStore mailFileStore = new MailFileStore();
            mailFileStore.setFileData(mailFile, mailFileName, mailFilePath, mailBoxId);

            mailFileStores.add(mailFileStore);
            mailFileStoreRepository.save(mailFileStore);
        }

        return mailFileStores.stream().map(mailFileStore -> new MailFileDTO(
            mailFileStore.getId(),
                mailFileStore.getMailFileName(),
                mailFileStore.getMailFileSize(),
                mailFileStore.getMailFileRegDate(),
                mailFileStore.getMailFileOriginName(),
                mailFileStore.getMailFilePath(),
                mailFileStore.getMailBoxFileId().getId(),
                mailFileStore.getMailFileType()
        )).toList();
    }

    public List<MailFileDTO> findByMailStoreId(Integer id) {
        List<MailFileDTO> byMailBoxId = mailFileStoreRepository.findByMailBoxId(id);
        return byMailBoxId;
    }


}
