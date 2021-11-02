package com.xzit.simpleftp.controller;


import com.xzit.simpleftp.entity.FileInfo;
import com.xzit.simpleftp.mapper.FileInfoMapper;
import com.xzit.simpleftp.utils.ResultUtil;
import com.xzit.simpleftp.utils.UUIDutil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    //获取日志输出对象
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileInfoMapper mapper;

    //上传文件接口
    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file){
        //设置日期格式"yyyyMMdd",目录格式是日期格式
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String filepath = simpleDateFormat.format(date)+"/";

        //判断目录是否存在.不存在,则创建
        File targetFile = new File(filepath);
        if(!targetFile.exists()) targetFile.mkdir();

        //生成UUID
        String uuid = UUIDutil.getUUID();
        //获取文件原始名字
        String fileName = file.getOriginalFilename();

        //获取文件相关信息
        if(!fileName.equals("")){
            //获取文件大小、文件类型，原始文件名、创建时间、文件保存目录地址
            long size = file.getSize();
            String filetype = file.getContentType();
            String OriginalFileName = file.getOriginalFilename();

            //获取当前日期
            date = new Date();

            //日期格式化
            simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String createtime = simpleDateFormat.format(date);

            //获取文件保存目录地址
            String filePath = filepath;

            //将文件信息等元数据记录至数据库中
            FileInfo fileInfo = new FileInfo(uuid, size, filetype, OriginalFileName, createtime, filepath);
            mapper.saveFileInfo(fileInfo);
            //获取文件后缀,并将后缀名改为小写
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            //生成最新的文件名
            String newFileName = uuid+"."+suffix;
            try {
                //文件输出流 目录+文件名
                FileOutputStream out = new FileOutputStream(filepath+newFileName);
                //写入数据
                out.write(file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                //打印日志
                logger.error("文件上传失败");
                return uuid;
            }
            //打印日志
            logger.info("文件上传成功");
            return uuid;
        }
        else{
            logger.error("文件不存在请重新上传");
            return uuid;
        }



    }
    //下载文件接口
    @GetMapping("/download")
    public String download(@RequestParam("uuid") String uuid, HttpServletResponse response) throws UnsupportedEncodingException {
        //根据uuid获取文件信息
        FileInfo fileInfo = mapper.getFileInfoById(uuid);

        //判断是否存在uuid的文件
        if(fileInfo!=null){
            //获取文件后缀
            String OriginalFileName = fileInfo.getOriginalfilename();
            String suffix = OriginalFileName.substring(OriginalFileName.lastIndexOf(".")+1).toLowerCase();

            //获取文件名和文件路径
            String filename = fileInfo.getId()+"."+suffix;
            String filePath = fileInfo.getFilepath();

            //获取文件
            File file = new File(filePath+filename);
            logger.info("文件路径:"+filePath+filename);

            //若文件存在则输出，否则返回410
            if(file.exists()){
                //响应数据
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));
                byte[] buffer = new byte[1024];
                //输出流
                OutputStream out = null;
                //文件输出流
                try {
                    FileInputStream input = new FileInputStream(file);
                    BufferedInputStream buff =new BufferedInputStream(input);

                    out = response.getOutputStream();
                    int i = buff.read(buffer);
                    while(i!=-1){
                        //写入数据
                        out.write(buffer);
                        i = buff.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //返回异常状态码
                    logger.error("异常状态码:410");
                    return "410";
                }
            }
            //返回异常状态码
            logger.error("异常状态码:410");
            return "410";



        }else{
            //返回异常状态码
            logger.error("异常状态码:410");
            return "410";
        }
    }

    //获取文件元数据接口
    @GetMapping("/getFileInfo")
    public ResultUtil getFileInfo(@RequestParam("uuid") String uuid){
        //获取元数据信息
        FileInfo file = mapper.getFileInfoById(uuid);
        if(file!=null){

            //返回JSON格式
            ResultUtil resultUtil = new ResultUtil(file);
            logger.info("元数据信息:"+resultUtil);
            return resultUtil;

        }
        else{
            //返回JSON格式
            ResultUtil resultUtil = new ResultUtil(200, "未找到该信息");
            return resultUtil;

        }

    }

}
