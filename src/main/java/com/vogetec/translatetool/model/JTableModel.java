package com.vogetec.translatetool.model;

import com.vogetec.translatetool.utils.ResourceXmlControler;
import com.vogetec.translatetool.utils.ResourceXmlTools;
import org.jdom.JDOMException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JTableModel extends BaseModel{
    private  List<ValueBean> valueBeans = new ArrayList<>();
    private  Object[][] data;
    private  String[] columnNames;
    public JTableModel(String path) {

        ResourceXmlTools resourceXmlControler =new ResourceXmlTools();
        try {
            //"F:\\java\\Workspaces\\VogetetFranslate\\res\\values\\strings.xml"
            resourceXmlControler.init(new File(path));
            valueBeans = resourceXmlControler.loadValueBeans();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        // 创建表头        String[] columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        if (valueBeans.size()>0)
        columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        //System.out.println(Arrays.toString(columnNames));
        data = createTable(valueBeans,columnNames.length);
    }

    public Object[][] getData() {
        return data;
    }

    public Object[] getColumnNames() {
        return columnNames;
    }


    public Object[][] createTable() {
        if (valueBeans.size()>0)
        columnNames = getColumnNames(valueBeans.get(0).getOtherColum());
        else
        columnNames = getColumnNames(new String[0]);
        return createTable(valueBeans,columnNames.length);
    }
    private Object[][] createTable(List<ValueBean> valueBeans, int length) {
        Object[][] objects =  new Object[valueBeans.size()][length];

        for (int i = 0; i < valueBeans.size(); i++) {

            objects[i][0] = valueBeans.get(i).key;

            objects[i][1] = simplifyPath(valueBeans.get(i).resFolder);

            objects[i][2] = valueBeans.get(i).unTransalteAble;

            objects[i][3] = valueBeans.get(i).needtranslate;

            for (int j = 0; j < valueBeans.get(i).langValues.size(); j++) {
              //  System.out.println("length"+length+"-"+valueBeans.get(i).langValues.size());
                objects[i][4+j]=valueBeans.get(i).langValues.get(j).langValue;
            }

        }
        return objects;
    }
    //优化路径算法
    /**方法一：栈
     思路与算法

     我们首先将给定的字符串 path\textit{path}path 根据 /\texttt{/}/ 分割成一个由若干字符串组成的列表，记为 names\textit{names}names。根据题目中规定的「规范路径的下述格式」，names\textit{names}names 中包含的字符串只能为以下几种：

     空字符串。例如当出现多个连续的 /\texttt{/}/，就会分割出空字符串；

     一个点 .\texttt{.}.；

     两个点 ..\texttt{..}..；

     只包含英文字母、数字或 _\texttt{\_}_ 的目录名。

     对于「空字符串」以及「一个点」，我们实际上无需对它们进行处理，因为「空字符串」没有任何含义，而「一个点」表示当前目录本身，我们无需切换目录。

     对于「两个点」或者「目录名」，我们则可以用一个栈来维护路径中的每一个目录名。当我们遇到「两个点」时，需要将目录切换到上一级，因此只要栈不为空，我们就弹出栈顶的目录。当我们遇到「目录名」时，就把它放入栈。

     这样一来，我们只需要遍历 names\textit{names}names 中的每个字符串并进行上述操作即可。在所有的操作完成后，我们将从栈底到栈顶的字符串用 /\texttt{/}/ 进行连接，再在最前面加上 /\texttt{/}/ 表示根目录，就可以得到简化后的规范路径。

     作者：力扣官方题解
     链接：https://leetcode.cn/problems/simplify-path/solutions/1193258/jian-hua-lu-jing-by-leetcode-solution-aucq/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String simplifyPath(String path) {
        char[] chrs = path.toCharArray();
        List<StringBuilder> Li = new ArrayList<>();
        StringBuilder SB = new StringBuilder("\\");
        int dots = 0;
        for (int i = 0; i < chrs.length; i++) {
            if (chrs[i] == '\\') {
                if (SB.length() > 1) {
                    if (dots == 2 && SB.length() == 3) {
                        if (Li.size() > 0)
                            Li.remove(Li.size() - 1);
                    } else if (dots != 1 || SB.length() != 2) {
                        Li.add(SB);
                    }
                    SB = new StringBuilder("\\");
                    dots = 0;
                }
            } else {
                SB.append(chrs[i]);
                if (chrs[i] == '.')
                    dots++;
            }
        }
        if (SB.length() > 1) {
            if (dots == 2 && SB.length() == 3) {
                if (Li.size() > 0)
                    Li.remove(Li.size() - 1);
            } else if (dots != 1 || SB.length() != 2) {
                Li.add(SB);
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder tmp : Li) {
            res.append(tmp);
        }
        if (res.length() == 0)
            return "\\";
        return res.toString();
    }
    public String[] getColumnNames(String[] columnNames){
        String[] c = {"Key", "Resource Folder", "UnTransalteAble","NeedTranslate"};
        String[] c2 = new String[c.length+columnNames.length];

        for (int i = 0; i < c.length; i++) {
            c2[i]=c[i];
        }
        for (int i = 0; i < columnNames.length; i++) {
            c2[c.length+i]=columnNames[i];
        }
        return c2;

    }

    public  List<ValueBean>  getSelectedData(){
        List<ValueBean> listValueBeans = new ArrayList<>();
        for (int i = 0; i < valueBeans.size(); i++) {
            if (valueBeans.get(i).needtranslate){
                listValueBeans.add(valueBeans.get(i));
            }
        }
        return listValueBeans;
    }

    public List<ValueBean> getValueBeans() {
        return valueBeans;
    }

    public void setValueBeans(List<ValueBean> valueBeans) {
        this.valueBeans = valueBeans;
    }
}
