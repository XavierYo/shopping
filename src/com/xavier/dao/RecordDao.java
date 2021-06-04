package com.xavier.dao;

import com.xavier.domain.Record;
import com.xavier.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RecordDao {
    public void record(int user_id,int item_id)throws SQLException {
        String sql="insert into record (user_id,item_id) values(?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,user_id,item_id);
    }
    public List<Record> getRecords()throws SQLException{
        String sql = "select * from record";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql,new BeanListHandler<>(Record.class));
    }
}
