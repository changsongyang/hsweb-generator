package org.hsweb.generator.swing.panel;


import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.panel.support.ShortCutsAdapter;
import org.hsweb.generator.swing.panel.support.ShortcutsCallBack;
import org.hsweb.generator.swing.utils.clipboard.ClipboardUtils;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Properties;

/**
 * 变量以及数据库配置面板
 * Created by 浩 on 2016-03-18 0018.
 */
public class VarPanel extends LayoutGeneratorPanel {
    protected Component[][] components;

    protected JTable table;

    protected final String columnNames[] = {"key", "value", "remark"};

    //设置快捷键支持
    protected ShortCutsAdapter shortCutsAdapter;
    /**
     * 默认配置
     */
    private Object[][] defaultVar = new Object[][]{
            {"jdbc.driver.class", "com.mysql.jdbc.Driver", "JDBC驱动系统(变量请勿删除)"},
            {"jdbc.url", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8", "数据库链接(变量请勿删除)"},
            {"jdbc.username", "root", "数据库用户名(变量请勿删除)"},
            {"jdbc.password", "root", "数据库密码(变量请勿删除)"},
            {"target", "/home/workspace/generator-test/", "代码输出目录"},
            {"function", "test", "功能(目录)名称"},
            {"beanName", "Test", "bean名称"}
    };

    /**
     * 创建表格
     */
    protected void createVarTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
        table = new JTable(model) {
            {
                setSize(SwingGeneratorApplication.WIDTH - 70, SwingGeneratorApplication.HEIGHT - 150);
                setRowMargin(4);
                setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                setRowHeight(25);
                setSelectionBackground(new Color(227, 227, 227));
            }
        };
        table.addKeyListener(shortCutsAdapter);
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
        //设置默认配置
        for (Object[] objects : defaultVar) {
            model.addRow(objects);
        }
    }

    /**
     * 删除选中的行
     */
    protected void removeSelectedRows() {
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

    /**
     * 创建面板上的组件
     */
    protected void createComponents() {
        components = new Component[][]{
                {
                        new JButton("添加") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{"", "", "新变量"});
                                }
                            });
                        }},
                        new JButton("删除") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    removeSelectedRows();
                                }
                            });
                        }},
                }
                ,
                {
                          new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, SwingGeneratorApplication.HEIGHT - 150);
                            setViewportView(table);
                        }}
                }
        };
    }

    /**
     * 初始化快捷键
     */
    public void initShortCuts() {
        shortCutsAdapter = new ShortCutsAdapter();
        shortCutsAdapter.bind("Ctrl+V", new ShortcutsCallBack() {
            @Override
            public void press() {
                //获取剪贴板上的配置到表格
                Properties properties = ClipboardUtils.getClipboardAsProperties();
                for (Object o : properties.keySet()) {
                    ((DefaultTableModel) table.getModel()).addRow(new Object[]{o, properties.getProperty(o.toString())});
                }
            }
        });
        shortCutsAdapter.bind("Ctrl+D", new ShortcutsCallBack() {
            @Override
            public void press() {
                removeSelectedRows();
            }
        });
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        super.init(application);
        initShortCuts();
        setLayout(null);
        createVarTable();
        createComponents();
        layoutComponent();
    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }

    @Override
    public String getTooltip() {
        return "变量设置";
    }

    @Override
    public String getTitle() {
        return "变量设置";
    }

    @Override
    public void onSelected() {

    }
}
