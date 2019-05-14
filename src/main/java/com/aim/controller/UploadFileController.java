package com.aim.controller;

import com.aim.common.FastDFSClient;
import com.aim.entity.File;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private CopyFile copyFile;
    @Value("${FILE_SERVER_URL}")
    String fsu;

    @RequestMapping("/uploadFile")
    public ModelAndView uploadFile(MultipartFile file) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String realPath = servletContext.getRealPath("/upload");
        //上传路径名
        System.out.println(realPath + originalFilename);
        String name = String.valueOf(originalFilename);
        //文件最终路径
        String rs = realPath +"/"+ name;
        System.out.println(rs);

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
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String path = fastDFSClient.uploadFile(file.getBytes(), extension, null);
            System.out.println(fsu + path);
            copyFile.copy(file,rs,originalFilename);
            modelAndView.setViewName("upload");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("upload");
            return modelAndView;
        }
    }
}
