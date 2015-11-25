package cn.edu.dhu.library.littlebee.config;

import org.hibernate.dialect.MySQLDialect;

/**
 * Created by sherry on 15-11-24.
 */
public class UTF8MySQLDialect extends MySQLDialect {

    @Override
    public String getTableTypeString() {
        return " DEFAULT CHARSET=utf8";
    }

}
