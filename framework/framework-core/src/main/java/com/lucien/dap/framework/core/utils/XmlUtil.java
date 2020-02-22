package com.lucien.dap.framework.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class XmlUtil {

    private static Map<Class<?>, JAXBContext> contextMapClass = new ConcurrentHashMap<>();

    public static String convertToXML(Object request, Class requestClazz) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext context = getJAXBContextByClass(requestClazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(request, writer);
            return writer.toString();
        } catch (Exception e) {
            log.error("将对象转为xml失败", e);
            return null;
        }
    }

    public static <T> T convertToObject(String response, Class<T> responseClazz) throws Exception {
        return convertToObject(response, responseClazz, "UTF-8");
    }

    public static <T> T convertToObject(String response, Class<T> responseClazz, String charset) throws Exception {
        T resultObject = null;
        JAXBContext context = getJAXBContextByClass(responseClazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        resultObject = responseClazz.cast(unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes(charset))));
        return resultObject;
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> T convertToObjectWithoutNs(String response, Class<?> responseClazz) throws Exception {
        T resultObject = null;
        JAXBContext jaxbContext = JAXBContext.newInstance(responseClazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(response);

        SAXParserFactory sax = SAXParserFactory.newInstance();
        sax.setNamespaceAware(false);
        XMLReader xmlReader = sax.newSAXParser().getXMLReader();

        Source source = new SAXSource(xmlReader, new InputSource(reader));
        resultObject = (T) unmarshaller.unmarshal(source);
        return resultObject;
    }

    protected static JAXBContext getJAXBContextByClass(Class<?> clazz) throws Exception {
        Class<?> inclass = getClass(clazz);
        JAXBContext context = contextMapClass.get(inclass);
        if (context == null) {
            context = JAXBContext.newInstance(inclass);
            contextMapClass.put(inclass, context);
        }
        return context;
    }

    private static Class<?> getClass(Class<?> interfaceClazz) throws Exception {
        String fullClassName = interfaceClazz.getName();
        return Class.forName(fullClassName);
    }
}
