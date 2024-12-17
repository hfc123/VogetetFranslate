package com.vogetec.translatetool.utils;

import org.jdom.output.XMLOutputter;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.Format;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
/*
*用于生成attribute换行的layout
* */
public class FeedLineXMLOutputter extends XMLOutputter {
    public FeedLineXMLOutputter() {
    }

    public FeedLineXMLOutputter(Format format) {
        super(format);
    }

    public FeedLineXMLOutputter(XMLOutputter that) {
        super(that);
    }

    @Override
    protected void printAttributes(Writer out, List attributes, Element parent, NamespaceStack namespaces) throws IOException {
        for(int i = 0; i < attributes.size(); ++i) {
            Attribute attribute = (Attribute)attributes.get(i);
            Namespace ns = attribute.getNamespace();
            out.write("\n");
            if (ns != Namespace.NO_NAMESPACE && ns != Namespace.XML_NAMESPACE) {
                try {
                    printNamespace2(out, ns, namespaces);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


            out.write(" ");
            try {
                printQualifiedName2(out, attribute);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            out.write("=");
            out.write("\"");
            out.write(this.escapeAttributeEntities(attribute.getValue()));
            out.write("\"");
        }
    }
    //利用反射调用私有方法
    private void printQualifiedName2(Writer out, Attribute attribute) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = XMLOutputter.class.getDeclaredMethod("printQualifiedName", new Class[]{Writer.class, Attribute.class});

        method.setAccessible(true);

        method.invoke(new XMLOutputter(), out, attribute);
    }
    //利用反射调用私有方法
    private void printNamespace2(Writer out, Namespace ns, NamespaceStack namespaces) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = XMLOutputter.class.getDeclaredMethod("printNamespace", new Class[]{Writer.class, Namespace.class, NamespaceStack.class});

        method.setAccessible(true);

        method.invoke(new XMLOutputter(), out, ns, namespaces);
    }

}
