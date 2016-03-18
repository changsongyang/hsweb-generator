package org.hsweb.generator.swing.panel;

import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.panel.support.FileChooserCellEditor;
import org.hsweb.generator.swing.panel.support.ShortCutsAdapter;
import org.hsweb.generator.swing.panel.support.ShortCutsListener;
import org.hsweb.generator.swing.utils.JTableUtils;
import org.webbuilder.office.excel.ExcelIO;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class GeneratorConfigPanel extends LayoutGeneratorPanel {

    protected final String columnNames[] = {"名称", "java类", "数据类型", "备注", "默认值", "主键", "不能为空", "自定义属性(json)"};

    protected final String templateColumnNames[] = {"模板名称", "模板类型", "备注", "输入", "输出"};

    private Component[][] components;

    private JTable fieldTable = null, templateTable = null;

    private ShortCutsAdapter fieldTableShortCuts = new ShortCutsAdapter();

    private ShortCutsAdapter templateTableShortCuts = new ShortCutsAdapter();

    @Override
    public String getTooltip() {
        return "代码生成";
    }

    @Override
    public String getTitle() {
        return "代码生成";
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        super.init(application);
        createComponents();
        layoutComponents();
    }

    private void createTemplateTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, templateColumnNames);
        templateTable = new JTable(model) {
            {
                getColumn("模板类型").setCellEditor(new DefaultCellEditor(new JComboBox() {{
                    this.addItem("文件模板");
                    this.addItem("数据库操作");
                }}));

                //输入
                FileChooserCellEditor input = new FileChooserCellEditor(new JTextField());
                input.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isFile())
                            return true;
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "选择模板文件";
                    }
                });
                getColumn("输入").setCellEditor(input);

                //输出目录
                FileChooserCellEditor output = new FileChooserCellEditor(new JTextField());
                output.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        if (f.isDirectory())
                            return true;
                        return false;
                    }

                    @Override
                    public String getDescription() {
                        return "选择输出目录";
                    }
                });
                getColumn("输出").setCellEditor(output);

                setSize(SwingGeneratorApplication.WIDTH - 70, 190);
                setRowMargin(2);
                setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                setRowHeight(18);
                setSelectionBackground(new Color(227, 227, 227));
            }
        };
        initTemplateTableShortCuts();
        templateTable.addKeyListener(templateTableShortCuts);
        templateTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
    }

    private void createFieldTable() {
        final Object[][] cellData = new Object[][]{};
        final DefaultTableModel model = new DefaultTableModel(cellData, columnNames);
        fieldTable = new JTable(model) {
            {
                getColumn("java类").setCellEditor(new DefaultCellEditor(new JComboBox() {{
                    this.addItem("byte");
                    this.addItem("int");
                    this.addItem("boolean");
                    this.addItem("double");
                    this.addItem("float");
                    this.addItem("String");
                    this.addItem("java.util.Date");
                }}));
                getColumn("主键").setCellEditor(new DefaultCellEditor(new JComboBox() {{
                    this.addItem("true");
                    this.addItem("false");
                }}));

                setSize(SwingGeneratorApplication.WIDTH - 70, 190);
                setRowMargin(2);
                setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                setRowHeight(18);
                setSelectionBackground(new Color(227, 227, 227));

            }
        };
        initFieldTableShortCuts();
        fieldTable.addKeyListener(fieldTableShortCuts);
        fieldTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
    }

    protected void createComponents() {
        createFieldTable();
        createTemplateTable();
        components = new Component[][]{
                //第一行
                {
                        new JButton("添加") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((DefaultTableModel) fieldTable.getModel()).addRow(new Object[]{"", "String", "varchar2(256)", "新建字段", "", false, false, "{}"});
                                }
                            });
                        }},
                        new JButton("删除") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JTableUtils.removeSelectedRows(fieldTable);
                                }
                            });
                        }},
                        new JButton("导入excel") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            setMargin(new Insets(0, 0, 0, 0));
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFileChooser chooser = new JFileChooser();
                                    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                    chooser.setFileFilter(new FileFilter() {
                                        @Override
                                        public boolean accept(File f) {
                                            if (f.isDirectory()) return true;
                                            return f.getName().endsWith("xls") || f.getName().endsWith("xlsx");
                                        }

                                        @Override
                                        public String getDescription() {
                                            return "excel文档";
                                        }
                                    });
                                    chooser.setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                                    chooser.showOpenDialog(null);
                                    File f = chooser.getSelectedFile();
                                    if (f == null)
                                        return;
                                    if (f.getName() != "xls" && f.getName() != "xlsx") {
                                        try {
                                            java.util.List<Map<String, Object>> datas = ExcelIO.read2Map(new FileInputStream(f));
                                            logger.info("导入excel成功!");
                                        } catch (Exception e1) {
                                            logger.error("加载文件失败", e1);
                                        }
                                    } else {
                                        logger.info("格式错误，只支持xls和xlsx格式的文件！");
                                    }

                                }
                            });
                        }}
                },
                //第二行
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 200);
                            setViewportView(fieldTable);
                        }}
                },
                //第三行
                {
                        new JButton("添加") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ((DefaultTableModel) templateTable.getModel()).addRow(new Object[]{"Controller", "文件模板", "新建模板", ""});
                                }
                            });
                        }},
                        new JButton("删除") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JTableUtils.removeSelectedRows(templateTable);
                                }
                            });
                        }},
                }
                ,
                //第四行
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 200);
                            setViewportView(templateTable);
                        }}
                }
        };
    }

    protected void initTemplateTableShortCuts() {
        templateTableShortCuts.bind("Ctrl+V", new ShortCutsListener() {
            @Override
            public void press() {

            }
        });
        templateTableShortCuts.bind("Ctrl+D", new ShortCutsListener() {
            @Override
            public void press() {
                JTableUtils.removeSelectedRows(templateTable);
            }
        });
    }

    protected void initFieldTableShortCuts() {
        fieldTableShortCuts.bind("Ctrl+V", new ShortCutsListener() {
            @Override
            public void press() {

            }
        });
        fieldTableShortCuts.bind("Ctrl+D", new ShortCutsListener() {
            @Override
            public void press() {
                JTableUtils.removeSelectedRows(fieldTable);
            }
        });
    }

    @Override
    public void onSelected() {

    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }
}
