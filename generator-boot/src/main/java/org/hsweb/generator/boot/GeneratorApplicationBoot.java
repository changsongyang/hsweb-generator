package org.hsweb.generator.boot;

import org.hsweb.generator.swing.SwingGeneratorApplication;

/**
 * Created by 浩 on 2016-03-19 0019.
 */
public class GeneratorApplicationBoot {
    public static void main(String[] args) {
        GeneratorApplication application = new SwingGeneratorApplication();

        application.startup();
    }
}
