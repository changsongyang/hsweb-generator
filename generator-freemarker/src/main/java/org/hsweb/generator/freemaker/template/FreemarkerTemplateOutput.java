package org.hsweb.generator.freemaker.template;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.template.TemplateOutput;

import java.io.OutputStream;

/**
 * Created by 浩 on 2016-03-17 0017.
 */
public class FreemarkerTemplateOutput implements TemplateOutput {

    private OutputStream outputStream;

    private CodeTemplate template;

    public FreemarkerTemplateOutput(OutputStream out) {
        this.outputStream = out;
    }

    @Override
    public void output() {
        //输出模板到outputStream

    }

    @Override
    public void setTemplate(CodeTemplate template) {
        this.template = template;
    }
}
