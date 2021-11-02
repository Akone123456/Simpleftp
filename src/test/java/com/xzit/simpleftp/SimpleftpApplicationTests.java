package com.xzit.simpleftp;

import com.xzit.simpleftp.entity.FileInfo;
import com.xzit.simpleftp.mapper.FileInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SimpleftpApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private FileInfoMapper mapper;




}
