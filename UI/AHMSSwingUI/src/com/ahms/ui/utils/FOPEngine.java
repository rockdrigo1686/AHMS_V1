package com.ahms.ui.utils;

import java.io.File;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class FOPEngine {
    public static void convertToPDF(String xsltFileName, String xmlFileName, String outputFileName) throws Exception {
        File xsltFile = new File(xsltFileName);
        StreamSource xmlSource = new StreamSource(new File(xmlFileName));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out;
        out = new java.io.FileOutputStream(outputFileName);
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent,
                            out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(
                            xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

    public static void convertToFO(String xsltFileName, String xmlFileName, String outputFileName) throws Exception {
        File xsltFile = new File(xsltFileName);
        StreamSource xmlSource = new StreamSource(new File(xmlFileName));
        OutputStream out;
        out = new java.io.FileOutputStream(outputFileName);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(
                            xsltFile));
            Result res = new StreamResult(out);
            transformer.transform(xmlSource, res);
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }

}
