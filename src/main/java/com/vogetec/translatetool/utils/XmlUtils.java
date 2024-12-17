package com.vogetec.translatetool.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlUtils {
    public static void writeStringToXmlFile(String content, String fileName) {
        try {
            // 创建 DocumentBuilderFactory 实例
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // 创建 XML 文档
            Document doc = docBuilder.newDocument();

            // 将字符串内容解析为 XML 元素
            Node parsedNode = parseStringToXmlNode(doc, content);

            // 将解析后的节点的副本添加到新文档
            Node copiedNode = doc.importNode(parsedNode, true);
            doc.appendChild(copiedNode);

            // 将 XML 写入文件
            writeXmlToFile(doc, fileName);

            System.out.println("XML 文件已创建成功。");

        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Node parseStringToXmlNode(Document doc, String content) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new java.io.StringReader(content));
            Document parsedDoc = builder.parse(is);

            // 获取解析文档的根节点
            return parsedDoc.getDocumentElement();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeXmlToFile(Document document, String fileName) throws IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            Writer writer = new FileWriter(new File(fileName));
            StreamResult result = new StreamResult(writer);

            transformer.transform(source, result);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 在这里调用方法，传入字符串内容和目标文件名
        String content = "<person><name>John Doe</name><age>30</age><city>New York</city></person>";
        String fileName = "output.xml";

        writeStringToXmlFile(content, fileName);
    }
}
