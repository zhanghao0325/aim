//package com.aim.common;
//
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.sl.usermodel.PictureData;
//import org.apache.poi.ss.usermodel.Picture;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.File;
//import java.io.FileOutputStream;
//
//public class ToXml {
//    public String toXML(String filePath){
//
//        try{
//
//            POIFSFileSystem nPOIFSFileSystem = new POIFSFileSystem(new File(filePath));
//
//            HWPFDocument nHWPFDocument = new HWPFDocument(nPOIFSFileSystem);
//            WordToFoConverter nWordToHtmlConverter = new WordToFoConverter(
//                    DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//            PicturesManager nPicturesManager = new PicturesManager() {
//
//                public String savePicture(byte[] arg0, PictureData.PictureType arg1, String arg2, float arg3, float arg4) {
//                    //file:///F://20.vscode//iWorkP//temp//images//0.jpg
//                    //System.out.println("file:///"+PathMaster.getWebRootPath()+ java.io.File.separator + "temp"+java.io.File.separator+"images" + java.io.File.separator + arg2);
////              return  "file:///"+PathMaster.getWebRootPath()+java.io.File.separator +"temp"+java.io.File.separator+"images" + java.io.File.separator + arg2;
//                    return  "file:///"+PathMaster.getWebRootPath()+java.io.File.separator +"temp"+java.io.File.separator+"images" + java.io.File.separator + arg2;
//                }
//            };
//
//            nWordToHtmlConverter.setPicturesManager(nPicturesManager);
//            nWordToHtmlConverter.processDocument(nHWPFDocument);
//            String nTempPath = PathMaster.getWebRootPath()  + java.io.File.separator + "temp" + java.io.File.separator + "images" + java.io.File.separator;
//            File nFile = new File(nTempPath);
//
//            if (!nFile.exists()) {
//                nFile.mkdirs();
//            }
//            for (Picture nPicture : nHWPFDocument.getPicturesTable().getAllPictures()) {
//                nPicture.writeImageContent(new FileOutputStream(nTempPath + nPicture.suggestFullFileName()));
//            }
//            Document nHtmlDocument = nWordToHtmlConverter.getDocument();
//            OutputStream nByteArrayOutputStream = new FileOutputStream(OUTFILEFO);
//            DOMSource nDOMSource = new DOMSource(nHtmlDocument);
//            StreamResult nStreamResult = new StreamResult(nByteArrayOutputStream);
//
//
//            TransformerFactory nTransformerFactory = TransformerFactory.newInstance();
//            Transformer nTransformer = nTransformerFactory.newTransformer();
//
//            nTransformer.setOutputProperty(OutputKeys.ENCODING, "GBK");
//            nTransformer.setOutputProperty(OutputKeys.INDENT, "YES");
//            nTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
//
//            nTransformer.transform(nDOMSource, nStreamResult);
//
//            nByteArrayOutputStream.close();
//
//            return "";
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return "";
//    }
//}
