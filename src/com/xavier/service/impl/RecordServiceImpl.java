package com.xavier.service.impl;

import com.xavier.domain.Record;
import com.xavier.service.RecordService;
import com.xavier.utils.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordServiceImpl implements RecordService {

    @Override
    public void record(int user_id, int item_id) {
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="INSERT INTO record (user_id,item_id) VALUES("+user_id+","+item_id+")";
        dbc.update(sql);
        dbc.close();
    }

    @Override
    public List<Record> getRecords() {
        List<Record> records=new ArrayList<>();
        DataBaseConnection dbc=new DataBaseConnection();
        String sql="SELECT * FROM record";
        ResultSet rs=dbc.query(sql);
        try{
            while(rs.next()){
                Record record=new Record();
                record.setRecord_id(rs.getInt("record_id"));
                record.setUser_id(rs.getInt("user_id"));
                record.setItem_id(rs.getInt("item_id"));
                record.setBrowser_time(rs.getTimestamp("browser_time"));
                records.add(record);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return records;
    }
}
