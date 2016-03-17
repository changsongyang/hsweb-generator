package org.hsweb.generator;


import org.hsweb.generator.callback.GeneratorProcessCallBack;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ºÆ on 2016-03-17 0017.
 */
public class GeneratorConfiguration {
    private String name;

    private List<CodeTemplate> templates = new LinkedList<>();

    private GeneratorProcessCallBack callBack = new GeneratorProcessCallBack() {
        @Override
        public void process(int step, String message) {

        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodeTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(List<CodeTemplate> templates) {
        this.templates = templates;
    }

    public GeneratorProcessCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(GeneratorProcessCallBack callBack) {
        this.callBack = callBack;
    }

    public GeneratorConfiguration addTemplate(CodeTemplate template) {
        templates.add(template);
        return this;
    }
}
