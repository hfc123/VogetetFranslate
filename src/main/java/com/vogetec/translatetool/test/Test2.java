package com.vogetec.translatetool.test;

import com.vogetec.translatetool.utils.ResourceXmlControler;
import org.jdom.JDOMException;

import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) {
        ResourceXmlControler resourceXmlControler =new ResourceXmlControler();
        try {
            resourceXmlControler.init(new File("F:\\java\\Workspaces\\VogetetFranslate\\res\\values\\strings.xml"));
            resourceXmlControler.loadValueBeans();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
