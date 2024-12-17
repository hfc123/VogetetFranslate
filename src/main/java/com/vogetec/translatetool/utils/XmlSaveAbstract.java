package com.vogetec.translatetool.utils;

import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.tools.JavaFileManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public abstract class XmlSaveAbstract {
    protected boolean needSort;
    protected int type;
    protected Document document;
    protected Element rootElement;
    protected File file;
    protected Project mProject;
    protected PsiFile psiFile;
    abstract   Map<String,String> getDimensMap();
    abstract   Map<String,String> getStringMap();
    abstract   Map<String,String> getColorMap();
    abstract   Map<String,String> getMap();
    abstract   void saveMaps(Map<String, String> map);
    public void init (PsiFile psiFile, int type, Project mProject) throws JDOMException, IOException {
        Logger.error(psiFile.getVirtualFile().getPath());
        file = new File(psiFile.getVirtualFile().getPath());
        try {
            document = getDocumentbyVirtualFile(psiFile.getVirtualFile());
        } catch (SAXException e) {
            e.printStackTrace();
        }
        rootElement = document.getRootElement();
        this.type =type;
        this.mProject =mProject;

    }
    public void init (File rawFile) throws JDOMException, IOException {
        file = rawFile;
        try {
            document = getDocumentbyFile(file);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        rootElement = document.getRootElement();
        this.type =2;

        this.mProject =mProject;

    }
    public void saveXml(){
        // 设置换行Tab或空格
        Format format = Format.getPrettyFormat();
        format.setIndent("	");
        format.setEncoding("UTF-8");
        XMLOutputter outputer;
        if (type == Constants.LAYOUTXML){

            outputer= new FeedLineXMLOutputter(format);
        }else {
            outputer =new XMLOutputter(format);
        }
        // 5、利用outputer将document转换成xml文档
        String content=outputer.outputString(document);
        System.out.println(content);
        String c=content.replaceAll("\r\n","\n");
        XmlUtils.writeStringToXmlFile(content,"F:\\java\\Workspaces\\VogetetFranslate\\values\\newstrings.xml");
        // 通过路径获取 VirtualFile 对象
     //   VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByIoFile(file);
     //   VirtualFile virtualFile = VfsUtil.findFileByIoFile(file, true);

      //  VirtualFile virtualFile = VirtualFileManager.getInstance().refreshAndFindFileByUrl("file://" + file.getPath());

        //   final com.intellij.openapi.editor.Document document1 = FileDocumentManager.getInstance().getDocument(virtualFile);
       /*final com.intellij.openapi.editor.Document document1 = FileDocumentManager.getInstance().getDocument(psiFile.getVirtualFile());
        String c=content.replaceAll("\r\n","\n");

        document1.setText(c);

        Logger.info(c);
        systemReformatString(mProject,psiFile);*/
    }

    private void systemReformatString(Project mProject, PsiFile mFile) {
        // reformat class
        JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(mProject);
        Logger.info((mFile==null)+"");
        styleManager.optimizeImports(mFile);
        JavaFileManager javaFileManager;
        styleManager.shortenClassReferences(mFile);
        new ReformatCodeProcessor(mProject,mFile, null, false).runWithoutProgress();
    }
    public static Document getDocumentbyVirtualFile(VirtualFile file) throws IOException, SAXException, JDOMException {
        //1、通过DOM解析器获取一个工厂实例
        SAXBuilder builder = new SAXBuilder();
        //3、使用这个解析器对象获取xml文件
        final com.intellij.openapi.editor.Document document = FileDocumentManager.getInstance().getDocument(file);
        String str =document.getText();
        //2、通过工厂对象获取解析器对象;
        InputSource is = new InputSource(new StringReader(str));
        return builder.build(is);
    }
    public static Document getDocumentbyFile(File file) throws IOException, SAXException, JDOMException {
        //1、通过DOM解析器获取一个工厂实例
        SAXBuilder builder = new SAXBuilder();
//        String str =getTextByFile(file);
        String xmlContent = new String(Files.readAllBytes(Paths.get(file.getPath())));
        //2、通过工厂对象获取解析器对象;
        InputSource is = new InputSource(new StringReader(xmlContent));
        return builder.build(is);
    }

    private static String getTextByFile(File file) {
        try {
            // 文件路径
            String xmlFilePath = file.getPath();

            // 创建一个DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // 创建一个DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 解析XML文件并获取Document对象
            org.w3c.dom.Document document = builder.parse(xmlFilePath);

            // 获取根元素
            org.w3c.dom.Element rootElement = document.getDocumentElement();

            // 获取根元素的文本内容
            String rootText = rootElement.getTextContent();

            // 打印文本内容
            System.out.println("Root Element Text: " + rootText);
            return rootText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 保存 Document 到文件的方法
    private static void saveDocumentToFile(org.w3c.dom.Document document, String filePath) throws TransformerException {
        // 创建一个 TransformerFactory
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // 创建一个 Transformer
        Transformer transformer = transformerFactory.newTransformer();

        // 使用 Transformer 将 Document 写入文件
        transformer.transform(new DOMSource(document), new StreamResult(new File(filePath)));
    }

}
