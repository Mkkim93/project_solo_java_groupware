package com.group.application.file;

import com.group.application.board.dto.FileBoardDTO;
import com.group.domain.board.entity.FileBoard;
import com.group.domain.file.entity.FileStore;
import com.group.domain.file.repository.FileStoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.conversion.ObjectPath;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FileStoreService {

    @Value("${file.dir}")
    private String fileDir;

    private FileStoreRepository fileStoreRepository;

    public FileStoreService(FileStoreRepository fileStoreRepository) {
        this.fileStoreRepository = fileStoreRepository;
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<FileStore> fileStoreSave(FileBoard fileBoard, List<MultipartFile> files) throws IOException {
        List<FileStore> fileStores = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            String filePath = fileDir;
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File saveFile = new File(filePath, fileName);

            file.transferTo(saveFile);

            FileStore fileStore = new FileStore();
            fileStore.setOriginFileName(file.getOriginalFilename());
            fileStore.setFileName(fileName);
            fileStore.setFilePath(filePath+ fileName);
            fileStore.setFileSize(file.getSize());
            fileStore.setFileType(file.getContentType());
            fileStore.setFileBoardId(fileBoard);

            fileStores.add(fileStore);
            fileStoreRepository.save(fileStore);
        }

        return fileStores;
    }

    public List<FileBoardStoreDTO> findByStoreId(Integer id) {
        return fileStoreRepository.findByFileBoardStoreId(id);
    }

    public FileStore findByIdOnly(Integer id) {
        return fileStoreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not id"));
    }
}
