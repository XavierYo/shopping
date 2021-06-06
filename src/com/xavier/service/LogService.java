package com.xavier.service;

import com.xavier.domain.Log;

import java.sql.SQLException;
import java.util.List;

public interface LogService {
    List<Log> getAllLogs()throws SQLException;
    void writeLog(Log log)throws SQLException;
}
