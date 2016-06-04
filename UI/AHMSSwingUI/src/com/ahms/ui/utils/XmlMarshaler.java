package com.ahms.ui.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlMarshaler<T> {

    private String path;

    public XmlMarshaler(String path) {
        this.path = path;
    }

    public int parseObject(T obj) throws IOException {
        int response = 0;
        try {
            if (obj != null) {

                File file = new File(path);
                JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(obj, file);
//			jaxbMarshaller.marshal(obj, System.out);	
                response = 1;
            }
        } catch (JAXBException e) {
            response = -1;
            e.printStackTrace();
        }
        return response;
    }
}
