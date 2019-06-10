package com.aim.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.aim.common.DelFile;
import com.aim.common.SplitParagraph;
import com.aim.common.SplitParagraph1;
import com.aim.entity.UplodFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

@Component
public class ConverFile {
    @Autowired
    private SaveSqlController saveSqlController;
    @Autowired
    private CsrPdfRead csrPdfRead;
    @Autowired
    private UpFastdfs upFastdfs;
    @Autowired
    private SolrTemplate solrTemplate;
    private static final Logger log = Logger.getLogger(ConverFile.class);
    private static final String[] WORD_FORMAT =
            {"DOC", "doc"};
    private static final String[] WORD_FORMAT1 = {"DOCX", "docx"};
    private static final String[] POWERPOINT_FORMAT = {"PPT", "ppt", "PPTX", "pptx", "DPS", "dps"};
    private static final String[] PDF_FORMAT = {"PDF", "pdf"};
    private static final String[] TXT_FORMAT = {"TXT", "txt"};
    private static final String[] RTF_FORMAT = {"RTF", "rtf"};
    private static final String[] CSV_FORMAT = {"CSV", "csv"};
    private static final String[] IMAGE_FORMAT =
            {"PNG", "png", "JPG", "jpg", "JPEG", "jpeg", "BMP", "bmp"};

    /**
     * 文件转换入口
     *
     * @param inputFile 输出文件
     * @return 输出文件
     * @throws Exception exception
     */
    public String converFile(String inputFile)
            throws Exception {

        File file = new File(inputFile);
        if (!file.exists()) {
            return "inputFile not found";
        }
        String extension = FilenameUtils.getExtension(file.getName());
        String baseName = FilenameUtils.getBaseName(file.getName());

        String name = file.getName();
        if (Arrays.asList(WORD_FORMAT).contains(extension)) {


        } else if (Arrays.asList(WORD_FORMAT1).contains(extension)) {
            String topdf = DocxToPdf.doxcTopdf(inputFile);
            DelFile.del(inputFile);
            this.converFile(topdf);
        } else if (Arrays.asList(PDF_FORMAT).contains(extension)) {
            //pdf转换txt文件
             csrPdfRead.start(inputFile);

            String s1 = ReadtTxt.readTxt(inputFile+".txt");
//            String s2 = new String(s1.getBytes("utf-8"), "utf-8");
            String split = SplitKeyWords.split(s1);
            //上传复制后的文件
            String path = upFastdfs.up(inputFile);
            //格式化转化的txt文件
//            List<String> list = SplitParagraph.getParagraph(inputFile + ".txt", "GBK");
            List<String> paragraph = SplitParagraph1.getParagraph(split);
            for (String s : paragraph) {
                UplodFile uplodFile = new UplodFile();
                uplodFile.setId(UUID.randomUUID().toString());
                //设置标题
                    uplodFile.setTitle(baseName);
                if (null != s && !"".equals(s)) {
                    uplodFile.setDsc(s);
                }
                uplodFile.setUrl(path);
                //储存文件到solr索引库
                solrTemplate.saveBean(uplodFile, 1000);
            }
            //储存到mysql
            com.aim.entity.File file1 = new com.aim.entity.File();
            file1.setFileName(baseName);
            file1.setUploadDate(new Date());
            file1.setFileType(extension);
            file1.setDistPath(path);
            file1.setFileSize(file.length());
            saveSqlController.save(file1);
            DelFile.del(inputFile);
            //DelFile.del(inputFile+".txt");
        } else {
            log.info("文件解析工厂未创建");
            return null;
        }

        return inputFile;
    }
}

