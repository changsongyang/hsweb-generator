package org.hsweb.generator.freemaker.utils;

import java.util.Map;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public interface TemplateRender {
    String render(Map<String, Object> vars) throws Exception;
}
