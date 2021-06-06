package com.xavier.dao;

import com.xavier.domain.Log;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class LogDao {
    public List<Log> getAllLogs()throws SQLException{
        String sql = "select * from log";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Log>(Log.class));
    }

    public void writeLog(Log log)throws SQLException{
        String sql = "insert into log (operate,ip,user) values(?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,log.getOperate(),log.getIp(),log.getUser());
    }
}
