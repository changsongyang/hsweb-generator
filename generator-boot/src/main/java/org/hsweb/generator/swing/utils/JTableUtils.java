package org.hsweb.generator.swing.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class JTableUtils {
    public static void removeSelectedRows(JTable table){
        int selections[] = table.getSelectedRows();
        if (selections.length > 0)
            try {
                int lastIndex = selections[0];
                for (int i = selections.length; i > 0; i--) {
                    ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                }
                if (table.getRowCount() > 0) {
                    table.setRowSelectionInterval(lastIndex - 1, lastIndex - 1);
                }
            } catch (Exception e1) {
                if (table.getRowCount() > 0)
                    table.setRowSelectionInterval(0, 0);
            }
    }
}
