package com.test;


import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.Generator;
import org.hsweb.generator.GeneratorConfiguration;
import org.junit.Test;

public class SampleTest {
    /**
     * 最简单的生成
     */
    @Test
    public void test() {

        GeneratorConfiguration configuration = new GeneratorConfiguration();

        CodeTemplate test = new CodeTemplate("test", "test", new StringTemplateInput("class Test{}"), new StringTemplateOutput());

        configuration.addTemplate(test);

        Generator.execute(configuration);
    }
}
