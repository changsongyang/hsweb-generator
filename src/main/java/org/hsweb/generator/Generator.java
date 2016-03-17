package org.hsweb.generator;

/**
 * Created by ºÆ on 2016-03-17 0017.
 */
public final class Generator {
    public static final void execute(GeneratorConfiguration configuration) {
        int step = 1;
        configuration.getCallBack().process(step++, "start");
        for (CodeTemplate template : configuration.getTemplates()) {
            configuration.getCallBack().process(step, "out put template:" + template);
            template.getOutput().output();
        }
        configuration.getCallBack().process(step++, "done");
    }
}
