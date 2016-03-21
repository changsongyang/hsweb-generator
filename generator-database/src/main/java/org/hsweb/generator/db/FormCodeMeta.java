package org.hsweb.generator.db;

import org.hsweb.generator.CodeMeta;
import org.webbuilder.sql.FieldMetaData;
import org.webbuilder.utils.common.BeanUtils;
import org.webbuilder.utils.common.ClassUtils;
import org.webbuilder.utils.common.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 浩 on 2016-03-21 0021.
 */
public class FormCodeMeta implements CodeMeta {

    private FieldMetaData metaData = new FieldMetaData();

    private static final Map<String, Class> typeMapper = new HashMap<>();

    static {
        typeMapper.put("string", String.class);
        typeMapper.put("String", String.class);
        typeMapper.put("byte", byte.class);
        typeMapper.put("short", short.class);
        typeMapper.put("char", char.class);
        typeMapper.put("long", long.class);
        typeMapper.put("float", float.class);
        typeMapper.put("double", double.class);
        typeMapper.put("boolean", boolean.class);
        typeMapper.put("int", int.class);
        typeMapper.put("date", Date.class);
    }

    @Override
    public <T> T getProperty(String property) {
        Object data = BeanUtils.attr(property, metaData);
        if (data == null) {
            data = metaData.attr(property);
        }
        return ((T) data);
    }

    protected Class getTypeByName(String name) {
        Class type = typeMapper.get(name);
        if (type == null) {
            try {
                type = Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return type;
    }

    @Override
    public <T> T setProperty(String property, T object) {
        switch (property) {
            case "name":
                metaData.setName(String.valueOf(object));
                break;
            case "comment":
                metaData.setComment(String.valueOf(object));
                break;
            case "javaType":
                metaData.setJavaType(getTypeByName(String.valueOf(object)));
                break;
            case "dataType":
                metaData.setDataType(String.valueOf(object));
                break;
            case "notNull":
                metaData.setNotNull(StringUtils.isTrue(object));
                break;
            case "primaryKey":
                metaData.setPrimaryKey(StringUtils.isTrue(object));
                break;
            default:
                metaData.attr(property, object);
                break;
        }
        return object;
    }

    public FieldMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(FieldMetaData metaData) {
        this.metaData = metaData;
    }
}
