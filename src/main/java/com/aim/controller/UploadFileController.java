package com.aim.controller;

import com.aim.common.FastDFSClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
//    @Autowired
//    private  CsrPdfRead csrPdfRead;
    @Value("${FILE_SERVER_URL}")
    String fsu;
    @RequestMapping("/uploadFile")
    public ModelAndView uploadFile(MultipartFile file) throws Exception {
            ModelAndView modelAndView = new ModelAndView();
            //获取图片路径
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);

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
            String rs=fsu+path;
            System.out.println(fsu+path);
//            int i = originalFilename.indexOf(".");
//            String substring = originalFilename.substring(i);
////        System.out.println(substring);
//            if (".doc".equals(substring)){
//
//            }else if (".pdf".equals(substring)){
//                csrPdfRead.start(rs,originalFilename);
//            }
//            modelAndView.setViewName("upload");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
//            modelAndView.setViewName("upload");
            return modelAndView;
        }
    }
}
