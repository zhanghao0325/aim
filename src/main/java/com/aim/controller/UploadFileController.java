package com.aim.controller;

import com.aim.common.FastDFSClient;
import com.aim.common.SplitParagraph;
import com.aim.entity.File;
import com.aim.entity.UplodFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.solr.core.SolrTemplate;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private CopyFile copyFile;
    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${FILE_SERVER_URL}")
    String fsu;

    @RequestMapping("/uploadFile")
    public ModelAndView uploadFile(MultipartFile file) throws Exception {
        UplodFile uplodFile = new UplodFile();

        File file1 = new File();
        ModelAndView modelAndView = new ModelAndView();
        //获取文件名
        String originalFilename = file.getOriginalFilename();

        System.out.println(originalFilename);
        String realPath = servletContext.getRealPath("/upload");
        //上传路径名
        System.out.println(realPath + originalFilename);
        String name = String.valueOf(originalFilename);
        //文件最终路径
        String rs = realPath +"\\"+ name;
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
            List<String> list = SplitParagraph.getParagraph(rs + ".txt", "GBK");
            for (String s : list) {
                uplodFile.setId(UUID.randomUUID().toString());
                //设置标题
                uplodFile.setTitle(originalFilename);
                if (null!=s&&!"".equals(s)){
                    uplodFile.setDsc(s);
                }
                solrTemplate.saveBean(uplodFile,1000);
            }
            modelAndView.setViewName("upload");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("upload");
            return modelAndView;
        }
    }
}
