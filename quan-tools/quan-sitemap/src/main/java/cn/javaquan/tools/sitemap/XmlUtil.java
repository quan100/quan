package cn.javaquan.tools.sitemap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

/**
 * XML工具类.
 *
 * @author quan.icu
 * @since 1.0.0
 */
public class XmlUtil {

    /**
     * 文档转字符串.
     * @param document 文档
     * @return 字符串
     */
    public static String parse(Document document) {
        return document.asXML();
    }

    /**
     * 字符串转文档.
     * @param text 字符串
     * @return 文档
     */
    public static Document parse(String text) {
        Document document;
        try {
            document = DocumentHelper.parseText(text);
        }
        catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
        return document;
    }

    /**
     * 解析XML.
     * @param url url
     * @return 文档
     */
    public static Document parse(URL url) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(url);
        }
        catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
        return document;
    }

    /**
     * 写入到文件.
     * @param fileName 文件名
     * @param document 文档
     */
    public static void write(String fileName, Document document) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            XMLWriter writer = new XMLWriter(fileWriter);
            writer.write(document);
            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 写入到文件流.
     * @param out 输出流
     * @param document 文档
     */
    public static void write(OutputStream out, Document document) {
        // 文件格式化
        OutputFormat format = OutputFormat.createPrettyPrint();
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(out)) {
            XMLWriter writer = new XMLWriter(fileWriter, format);
            writer.write(document);
            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 输出到控制台.
     * @param document 文档
     */
    public static void prettyPrint(Document document) {
        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(System.out, format);
            writer.write(document);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /**
     * 输出到控制台.
     * @param document 文档
     */
    public static void compactPrint(Document document) {
        // Compact format to System.out
        OutputFormat format = OutputFormat.createCompactFormat();
        XMLWriter writer;
        try {
            writer = new XMLWriter(System.out, format);
            writer.write(document);
            writer.close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
