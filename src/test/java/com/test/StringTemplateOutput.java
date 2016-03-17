package com.test;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.template.TemplateOutput;

public class StringTemplateOutput implements TemplateOutput {
    private CodeTemplate template;

    @Override
    public void output() {
        System.out.println(template.getInput().read());
    }

    @Override
    public void setTemplate(CodeTemplate template) {
        this.template = template;
    }
}
