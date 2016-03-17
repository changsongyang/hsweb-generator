package com.test;

import org.hsweb.generator.template.TemplateInput;

/**
 * Created by ºÆ on 2016-03-17 0017.
 */
public class StringTemplateInput implements TemplateInput {

    public String template;

    public StringTemplateInput(String template) {
        this.template = template;
    }

    @Override
    public String read() {
        return template;
    }
}
