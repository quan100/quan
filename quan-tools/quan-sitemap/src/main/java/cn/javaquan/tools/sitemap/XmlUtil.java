package cn.javaquan.tools.sitemap;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.URL;

/**
 * @author quan.icu
 */
public class XmlUtil {

    /**
     * 文档转字符串
     *
     * @param document
     * @return
     */
    public static String parse(Document document) {
        String text = document.asXML();
        return text;
    }

    /**
     * 字符串转文档
     *
     * @param text
     * @return
     */
    public static Document parse(String text) {
        Document document;
        try {
            document = DocumentHelper.parseText(text);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    /**
     * 解析XML
     *
     * @param url
     * @return
     * @throws DocumentException
     */
    public static Document parse(URL url) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    /**
     * 快速循环
     *
     * @param document
     */
    public static void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    public static void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            } else {
                // do something…
            }
        }
    }

    /**
     * 写入到文件
     *
     * @param document
     * @throws IOException
     */
    public static void write(String fileName, Document document) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            XMLWriter writer = new XMLWriter(fileWriter);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入到文件流
     *
     * @param out
     * @param document
     */
    public static void write(OutputStream out, Document document) {
        // 文件格式化
        OutputFormat format = OutputFormat.createPrettyPrint();
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(out)) {
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 输出到控制台
     *
     * @param document
     */
    public static void prettyPrint(Document document) {
        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(System.out, format);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.write(document);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 输出到控制台
     *
     * @param document
     */
    public static void compactPrint(Document document) {
        // Compact format to System.out
        OutputFormat format = OutputFormat.createCompactFormat();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(System.out, format);
            writer.write(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
