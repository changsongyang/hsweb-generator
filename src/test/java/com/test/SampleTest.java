package com.test;


import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.Generator;
import org.hsweb.generator.GeneratorConfiguration;
import org.junit.Test;

/**
 * Created by ºÆ on 2016-03-17 0017.
 */
public class SampleTest {
    @Test
    public void  test() {

        GeneratorConfiguration configuration = new GeneratorConfiguration();

        CodeTemplate test = new CodeTemplate("test", "test", new StringTemplateInput("class Test{}"), new StringTemplateOutput());

        configuration.addTemplate(test);

        Generator.execute(configuration);
    }
}
