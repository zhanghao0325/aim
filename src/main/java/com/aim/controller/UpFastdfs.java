package com.aim.controller;

import com.aim.common.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Component
public class UpFastdfs {
    @Value("${FILE_SERVER_URL}")
    String fsu;
    public String up(String path){
        File file = new File(path);
        try {
            //创建跟踪器
//            TrackerClient trackerClient = new TrackerClient();
            //获取连接
//            TrackerServer connection = trackerClient.getConnection();
            //这是储存节点  存入返回的路径
//            StorageClient1 storageClient1 = new StorageClient1(connection,null);
            //储存节点存储文件                         路径                格式              描述
//            String path= storageClient1.upload_file1(originalFilename, "jpg", null);
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
            String extension = FilenameUtils.getExtension(file.getName());
            String path1 = fastDFSClient.uploadFile(path, extension, null);
            System.out.println(fsu + path1);
//
            return fsu+path1;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }

    }
}
