package org.hsweb.generator.freemaker.utils;

import freemarker.cache.StringTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.webbuilder.utils.common.BeanUtils;
import org.webbuilder.utils.common.ClassUtils;
import org.webbuilder.utils.common.StringUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public class TemplateUtils {

    //freemarker字符串模板加载器
    private static final StringTemplateLoader loader = new StringTemplateLoader();
    //freemarker配置器
    private static final Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_23);

    static {
        //初始化
        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
        freemarkerCfg.setTemplateLoader(loader);
    }

    private static MethodUtils methodUtils = new MethodUtils();
    /**
     * 编译模板并装载模板
     *
     * @param templateStr 模板内容
     * @return 编译是否成功
     * @throws Exception 异常信息，如模板未找到
     */
    public static TemplateRender compile(String templateStr) throws Exception {
        StringTemplateLoader templateLoader = ((StringTemplateLoader) freemarkerCfg.getTemplateLoader());
        String name = StringUtils.concat("template.", templateStr.hashCode());
        templateLoader.putTemplate(name, templateStr);
        freemarkerCfg.setTemplateLoader(templateLoader);
        final String finalName = name;
        return new TemplateRender() {
            @Override
            public String render(Map<String, Object> vars) throws Exception {
                Template template = freemarkerCfg.getTemplate(finalName);
                StringWriter out = new StringWriter();
                vars.put("utils",methodUtils);
                template.process(vars, out);

                return out.getBuffer().toString();
            }
        };
    }

}
