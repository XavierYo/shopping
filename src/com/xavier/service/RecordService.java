package com.xavier.service;

import com.xavier.domain.Record;

import java.util.List;

public interface RecordService {
    void record(int user_id,int item_id);
    List<Record> getRecords();
}
