package com.xzit.simpleftp.mapper;


import com.xzit.simpleftp.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileInfoMapper {
    int saveFileInfo(FileInfo fileInfo);
    FileInfo getFileInfoById(String id);
}
