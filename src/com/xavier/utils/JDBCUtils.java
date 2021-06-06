package com.xavier.utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * 创建数据库连接池
 */
public class JDBCUtils {
    // 创建一个ThreadLoacl对象，用当前线程作为key
    private static ThreadLocal<Connection> tc = new ThreadLocal<Connection>();
    // 读取的是C3P0-config默认配置创建数据库连接池对象
    private static DataSource ds = new ComboPooledDataSource();
    // 获取数据库连接池对象
    public static DataSource getDataSource() {
        return ds;
    }
    // 从连接池中获取连接
    public static Connection getConnection() throws SQLException {
        Connection conn = tc.get();
        if (conn == null) {
            conn = ds.getConnection();
            // 将conn存放到集合tc中
            tc.set(conn);
            System.out.println("首次创建连接：" + conn);
        }
        return conn;
    }

    // 开启事务
    public static void startTransaction() {
        try {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void commit() {
        try {
            Connection conn = tc.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            // 从集合tc中得到一个连接
            Connection conn = tc.get();
            if (conn != null) {
                // 该方法用于取消在当前事务中进行的更改，并释放当前Connection对象持有的所有数据库锁。此方法只有在手动事务模式下才可用
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}