package org.hsweb.generator.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webbuilder.utils.file.FileUtils;

import java.io.File;
import java.util.Map;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public class FileTemplateInput implements TemplateInput {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public File file;

    private Map<String, Object> config;

    public FileTemplateInput(File file) {
        this.file = file;
    }

    @Override
    public String read() throws Exception {
        try {
            return FileUtils.readFile2String(file.getAbsolutePath());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}
