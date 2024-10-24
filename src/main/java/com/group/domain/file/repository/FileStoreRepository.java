package com.group.domain.file.repository;

import com.group.application.file.FileBoardStoreDTO;
import com.group.domain.file.entity.FileStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, Integer> {



    /*@Query("select f.id, s.id, s.fileSize, s.fileName, s.filePath, s.fileRegDate " +
            "from FileStore s, FileBoard f " +
            "where s.fileBoardId.id = f.id and s.fileBoardId.id =:id")
    List<Object[]> findByFileBoardStoreId(@Param("id") Integer id);*/

    @Query("select new com.group.application.file.FileBoardStoreDTO(s.id, s.fileName, s.fileSize, s.fileType, s.filePath, s.fileRegDate, s.fileBoardId.id, s.OriginFileName) " +
            "from FileStore s, FileBoard f " +
            "where s.fileBoardId.id = f.id and s.fileBoardId.id =:id")
    List<FileBoardStoreDTO> findByFileBoardStoreId(@Param("id") Integer id);
}
