package com.yuntian.jdbc;

/**
 * @author guangleilei.
 * @date Created in 16:49 2020/1/10
 * @description
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author guangleilei
 * @ClassName: JdbcUtils
 * @date 2019年01月22日
 * @Description: TODO
 */

public class JDBCUtils {

    private JDBCUtils() {
    }

    private static DataSource dataSource = null;

    // 静态代码块，加载配置文件。
    static {
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties prop = new Properties();
            prop.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(prop);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }


    /**
     * 定义公有的得到数据源的方法
     *
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 创建数据库连接实例
     *
     * @return 数据库连接实例 connection
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取数据库连接异常");
    }


    /**
     * 关闭数据库连接实例
     */
    public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement,  Connection connection) {
        releaseSqlConnection(rSet,pStatement,null,connection);
    }
    /**
     * 关闭数据库连接实例
     */
    public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement, PreparedStatement iStatement, Connection connection) {
        try {
            if (rSet != null) {
                rSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (iStatement != null) {
                    iStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}