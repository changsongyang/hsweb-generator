package org.hsweb.generator.swing.panel;

import org.hsweb.generator.boot.register.PropertiesRegister;
import org.hsweb.generator.swing.SwingGeneratorApplication;
import org.hsweb.generator.swing.logger.JTextAreaLoggerAppender;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class StartGeneratorPanel extends LayoutGeneratorPanel {

    protected Component components[][];

    protected JTextArea console;

    public void createComponents() {
        console = new JTextArea() {{
            setSize(SwingGeneratorApplication.WIDTH - 50, 150);
            setText(">控制台准备就绪!\n");
            setAutoscrolls(true);
            setEditable(false);
            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
            setForeground(Color.black);
        }};
        JTextAreaLoggerAppender.bindTextArea(console);
        components = new Component[][]{
                {
                        new JButton("开始") {{
                            setSize(80, 25);
                            setFont(SwingGeneratorApplication.BASIC_FONT_MIN);
                            addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    startGenerator();
                                }
                            });
                        }}
                },
                {
                        new JScrollPane() {{
                            setSize(SwingGeneratorApplication.WIDTH - 50, 200);
                            setViewportView(console);
                        }}
                }
        };
    }

    @Override
    public void init(SwingGeneratorApplication application) {
        super.init(application);
        createComponents();
        layoutComponents();
    }

    @Override
    public Component[][] getComponentArray() {
        return components;
    }

    @Override
    public String getTooltip() {
        return "生成代码";
    }

    @Override
    public String getTitle() {
        return "生成代码";
    }

    @Override
    public void onSelected() {

    }

    protected void startGenerator() {
        //获取配置
        PropertiesRegister propertiesRegister = application.getRegister(PropertiesRegister.class);
        Properties config = propertiesRegister.getMergedData();
        logger.debug("获取配置:" + config);
    }
}
