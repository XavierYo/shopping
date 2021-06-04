package com.xavier.service.impl;
import com.xavier.dao.RecordDao;
import com.xavier.domain.Record;
import com.xavier.service.RecordService;
import java.sql.SQLException;
import java.util.List;

public class RecordServiceImpl implements RecordService {

    @Override
    public void record(int user_id, int item_id) throws SQLException {
        RecordDao recordDao = new RecordDao();
        recordDao.record(user_id,item_id);
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        RecordDao recordDao = new RecordDao();
        return recordDao.getRecords();
    }
}
