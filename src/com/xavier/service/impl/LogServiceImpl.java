package com.xavier.service.impl;

import com.xavier.dao.LogDao;
import com.xavier.domain.Log;
import com.xavier.service.LogService;

import java.sql.SQLException;
import java.util.List;

public class LogServiceImpl implements LogService {

    @Override
    public List<Log> getAllLogs() throws SQLException {
        LogDao logDao = new LogDao();
        return logDao.getAllLogs();
    }

    @Override
    public void writeLog(Log log) throws SQLException {
        LogDao logDao = new LogDao();
        logDao.writeLog(log);
    }
}
