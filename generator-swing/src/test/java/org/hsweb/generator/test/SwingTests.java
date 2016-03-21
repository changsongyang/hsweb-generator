package org.hsweb.generator.test;

import org.hsweb.generator.app.register.CodeTemplateRegister;
import org.hsweb.generator.app.register.MetaRegister;
import org.hsweb.generator.swing.SwingGeneratorApplication;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class SwingTests {

    public static void main(String[] args) {
        SwingGeneratorApplication application = new SwingGeneratorApplication();
        application.setRegister(CodeTemplateRegister.class, new CodeTemplateRegister());
        application.setRegister(MetaRegister.class, new MetaRegister());
        application.startup();
    }
}
