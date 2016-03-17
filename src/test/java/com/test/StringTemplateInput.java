package com.test;

import org.hsweb.generator.template.TemplateInput;

import java.util.Map;

public class StringTemplateInput implements TemplateInput {

    public String template;

    public StringTemplateInput(String template) {
        this.template = template;
    }

    @Override
    public Map<String, Object> getConfig() {
        return null;
    }

    @Override
    public String read() {
        return template;
    }
}
