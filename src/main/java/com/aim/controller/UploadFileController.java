package com.aim.controller;

import com.aim.common.FastDFSClient;
import com.aim.common.SplitParagraph;
import com.aim.entity.File;
import com.aim.entity.Result;
import com.aim.entity.UplodFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.solr.core.SolrTemplate;
import javax.servlet.ServletContext;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadFileController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private CopyFile copyFile;
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private SaveSqlController saveSqlController;
//    @Value("${FILE_SERVER_URL}")
//    String fsu;

    @RequestMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) throws Exception {


        try {

            //File file1 = new File();

            //获取文件名
            String originalFilename = file.getOriginalFilename();
            //file1.setFileName(originalFilename);
            System.out.println(originalFilename);
            String realPath = servletContext.getRealPath("/upload");
            //上传路径名
            String name = String.valueOf(originalFilename);
            //文件最终路径
            String rs = realPath +"\\"+ name;
            System.out.println(rs);

            //复制文件到指定路径
            copyFile.copy(file,rs);
            //创建跟踪器
//            TrackerClient trackerClient = new TrackerClient();
            //获取连接
//            TrackerServer connection = trackerClient.getConnection();
            //这是储存节点  存入返回的路径
//            StorageClient1 storageClient1 = new StorageClient1(connection,null);
            //储存节点存储文件                         路径                格式              描述
//            String path= storageClient1.upload_file1(originalFilename, "jpg", null);
//            FastDFSClient fastDFSClient = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
//            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//            String path = fastDFSClient.uploadFile(file.getBytes(), extension, null);
//            System.out.println(fsu + path);
//            file1.setDistPath(fsu+path);
//            file1.setUploadDate(new Date());
//            int i = originalFilename.indexOf(".");
//            String substring = originalFilename.substring(i);
//            file1.setFileType(substring);
//            saveSqlController.save(file1);


            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
}
