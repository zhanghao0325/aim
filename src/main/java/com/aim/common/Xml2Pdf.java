//package com.aim.common;
//
//
//import org.omg.CORBA_2_3.portable.OutputStream;
//import org.xml.sax.SAXException;
//import sun.net.www.URLConnection;
//
//import javax.print.DocFlavor;
//import javax.xml.transform.*;
//import javax.xml.transform.sax.SAXResult;
//import javax.xml.transform.stream.StreamSource;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import static javax.servlet.jsp.PageContext.CONFIG;
//
//
//public class Xml2Pdf {
//    public void xmlToPDF() throws SAXException, TransformerException {
//        // Step 1: Construct a FopFactory by specifying a reference to the configuration file
//        // (reuse if you plan to render multiple documents!)
//        FopFactory fopFactory = null;
//        new URIResolverAdapter(new URIResolver(){
//            public Source resolve(String href, String base) throws TransformerException {
//                try {
//                    DocFlavor.URL url = new DocFlavor.URL(href);
//                    URLConnection connection = url.openConnection();
//                    connection.setRequestProperty("User-Agent", "whatever");
//                    return new StreamSource(connection.getInputStream());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        OutputStream out = null;
//        try {
//
//            fopFactory = FopFactory.newInstance(new File(CONFIG));
//
//            // Step 2: Set up output stream.
//            // Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
//
//            out = new BufferedOutputStream(new FileOutputStream(OUTFILEPDF));
//
//            // Step 3: Construct fop with desired output format
//            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
//
//            // Step 4: Setup JAXP using identity transformer
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer transformer = factory.newTransformer(); // identity transformer
//
//            // Step 5: Setup input and output for XSLT transformation
//            // Setup input stream
//            Source src = new StreamSource(OUTFILEFO);
//
//            // Resulting SAX events (the generated FO) must be piped through to FOP
//            Result res = new SAXResult(fop.getDefaultHandler());
//            // Step 6: Start XSLT transformation and FOP processing
//            transformer.transform(src, res);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            //Clean-up
//            try {
//                out.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }}
//}
