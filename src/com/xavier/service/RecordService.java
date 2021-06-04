package com.xavier.service;

import com.xavier.domain.Record;

import java.sql.SQLException;
import java.util.List;

public interface RecordService {
    void record(int user_id,int item_id)throws SQLException;
    List<Record> getRecords()throws SQLException;
}
