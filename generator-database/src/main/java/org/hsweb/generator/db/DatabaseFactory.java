package org.hsweb.generator.db;


import org.webbuilder.sql.DataBase;
import org.webbuilder.sql.DataBaseMetaData;
import org.webbuilder.sql.support.MysqlDataBaseMetaData;
import org.webbuilder.sql.support.OracleDataBaseMetaData;
import org.webbuilder.sql.support.common.CommonDataBase;

import java.util.Properties;

/**
 * 数据库工厂，用于创建进行数据库操作的对象.
 * 目前只支持mysql和oracle数据库
 * Created by 浩 on 2016-03-17 0017.
 */
public class DatabaseFactory {

    private DatabaseFactory() {
    }

    /**
     * 根据配置创建一个mysql数据库操作对象=
     *
     * @param jdbcProperties JDBC配置文件<br/>
     *                       配置文件应包含以下配置:
     *                       <ul>
     *                       <li>jdbc.driver.class</li>
     *                       <li>jdbc.url</li>
     *                       <li>jdbc.username</li>
     *                       <li>jdbc.password</li>
     *                       </ul>
     * @return mysql数据库操作对象
     */
    public static DataBase createMysqlDatabase(Properties jdbcProperties) {
        DataBaseMetaData metaData = new MysqlDataBaseMetaData();
        DataBase dataBase = new CommonDataBase(metaData, new CommonJdbcSqlExecutor(jdbcProperties));
        return dataBase;
    }

    /**
     * 根据配置创建一个oracle数据库操作对象=
     *
     * @param jdbcProperties JDBC配置文件<br/>
     *                       配置文件应包含以下配置:
     *                       <ul>
     *                       <li>jdbc.driver.class</li>
     *                       <li>jdbc.url</li>
     *                       <li>jdbc.username</li>
     *                       <li>jdbc.password</li>
     *                       </ul>
     * @return oracle数据库操作对象
     */
    public static DataBase createOracleDatabase(Properties jdbcProperties) {
        DataBaseMetaData metaData = new OracleDataBaseMetaData();
        DataBase dataBase = new CommonDataBase(metaData, new CommonJdbcSqlExecutor(jdbcProperties));
        return dataBase;
    }

    public static DataBase createDatabase(Properties properties) {
        if (String.valueOf(properties.get("jdbc.driver.class")).contains("mysql"))
            return createMysqlDatabase(properties);
        else
            return createOracleDatabase(properties);
    }
}
