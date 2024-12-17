package com.vogtec.hfc.test;

import com.intellij.psi.PsiElement;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MyTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return column!=1;
    }
}
