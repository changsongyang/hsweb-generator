package org.hsweb.generator.swing;

import org.hsweb.generator.boot.GeneratorApplication;
import org.hsweb.generator.swing.logger.JTextAreaLoggerAppender;
import org.hsweb.generator.swing.panel.GeneratorConfigPanel;
import org.hsweb.generator.swing.panel.GeneratorPanel;
import org.hsweb.generator.swing.panel.VarPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class SwingGeneratorApplication extends JFrame implements GeneratorApplication {
    public static final int WIDTH = 800, HEIGHT = 600;

    public static final Font BASIC_FONT = new Font("微软雅黑", Font.BOLD, 18);

    public static final Font BASIC_FONT_MIN = new Font("微软雅黑", Font.BOLD, 13);

    private java.util.List<GeneratorPanel> panels = new LinkedList<>();

    public void registerPanel(GeneratorPanel panel) {
        panels.add(panel);
    }

    public SwingGeneratorApplication() {
        //创建主窗体信息
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("代码生成器 v1.0 by github.com/hs-web");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);//居中
        this.setResizable(false);
        this.setBackground(new Color(43, 43, 43));
        this.setForeground(new Color(43, 43, 43));
        this.setFont(BASIC_FONT);
    }

    protected void renderPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(BASIC_FONT_MIN);
        //加入panel到选项卡
        for (GeneratorPanel panel : panels) {
            tabbedPane.addTab(panel.getTitle(), null, panel, panel.getTooltip());
            panel.init(this);
        }
        //触发选中第一个
        if (panels.size() > 0) {
            panels.get(0).onSelected();
        }
        //添加切换选项卡事件监听器
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = ((JTabbedPane) e.getSource()).getSelectedIndex();
                panels.get(index).onSelected();
            }
        });
        this.add(tabbedPane);
    }

    public JLabel createLabel(String name) {
        JLabel label = new JLabel(name, SwingConstants.RIGHT);
        label.setFont(BASIC_FONT);
        label.setSize(100, 20);
        return label;
    }

    public JTextField createInput() {
        JTextField textField = new JTextField();
        textField.setFont(BASIC_FONT_MIN);
        textField.setSize(100, 25);
        return textField;
    }

    @Override
    public String getName() {
        return "swing";
    }

    @Override
    public void startup() {
        registerPanel(new VarPanel());
        registerPanel(new GeneratorConfigPanel());
        renderPanel();
        this.setVisible(true);
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

}
