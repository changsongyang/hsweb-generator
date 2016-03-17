package org.hsweb.generator.template;


import org.hsweb.generator.CodeTemplate;

/**
 * 模板输出接口，通过此接口，将模板输出。
 * Created by 浩 on 2016-03-17 0017.
 */
public interface TemplateOutput {
    void output();

    void setTemplate(CodeTemplate template);
}
