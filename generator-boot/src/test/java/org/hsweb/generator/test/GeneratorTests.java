package org.hsweb.generator.test;

import org.hsweb.generator.CodeTemplate;
import org.hsweb.generator.Generator;
import org.hsweb.generator.GeneratorConfiguration;
import org.hsweb.generator.db.DatabaseFactory;
import org.hsweb.generator.db.template.TableTemplateInput;
import org.hsweb.generator.db.template.TableTemplateOutput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.webbuilder.sql.DataBase;
import org.webbuilder.sql.FieldMetaData;
import org.webbuilder.sql.TableMetaData;
import org.webbuilder.sql.parser.CommonTableMetaDataParser;
import org.webbuilder.utils.file.FileUtils;

import java.io.IOException;

/**
 * Created by 浩 on 2016-03-17 0017.
 */
public class GeneratorTests {

    private DataBase dataBase;

    @Before
    public void setup() throws IOException {
        dataBase = DatabaseFactory.createMysqlDatabase(FileUtils.getResourceAsProperties("jdbc.properties"));
    }

    @After
    public void over() throws IOException {
    }

    @Test
    public void simpleTest() {
        //创建一个表结构
        TableMetaData metaData = new TableMetaData();
        metaData.setName("generator_test");
        metaData.addField(new FieldMetaData("uid", String.class, "varchar(32)"));
        metaData.addField(new FieldMetaData("uname", String.class, "varchar(64)"));

        //创建生成配置
        GeneratorConfiguration configuration = new GeneratorConfiguration();
        //创建一个生成数据库表接口的模板
        CodeTemplate codeTemplate = new CodeTemplate();
        codeTemplate.setInput(new TableTemplateInput(metaData));
        codeTemplate.setOutput(new TableTemplateOutput(dataBase));
        configuration.addTemplate(codeTemplate);

        //开始生成
        Generator.execute(configuration);
    }
}
