package org.hsweb.generator.test;

import org.hsweb.generator.boot.GeneratorApplication;
import org.hsweb.generator.swing.SwingGeneratorApplication;

/**
 * Created by 浩 on 2016-03-18 0018.
 */
public class SwingTests {

    public static void main(String[] args) {
        GeneratorApplication application = new SwingGeneratorApplication();
        application.startup();
    }
}
