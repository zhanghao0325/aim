package com.aim.controller;

import com.aim.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
@Component
public class CopyFile {
    @Autowired
    private CsrPdfRead csrPdfRead;
    public void copy(MultipartFile file,String rs,String originalFilename) throws SQLException, InterruptedException {
        try {
            //创建file对象
            File writerFile = new File(rs);
            //如果没有文件  创建
            if (!writerFile.isDirectory()) {
                writerFile.mkdirs();
            }
            //将文件复制到指定路径下
            file.transferTo(writerFile);
            System.out.println("success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = originalFilename.indexOf(".");
        String substring = originalFilename.substring(i);
//        System.out.println(substring);
        if (".doc".equals(substring)) {

        } else if (".pdf".equals(substring)) {
            csrPdfRead.start(rs);
        }
    }
}
