package com.xavier.domain;

import java.sql.Timestamp;

public class Record {
    private int record_id;
    private int user_id;
    private int item_id;
    private Timestamp browser_time;
    public Timestamp getBrowser_time() {
        return browser_time;
    }

    public void setBrowser_time(Timestamp browser_time) {
        this.browser_time = browser_time;
    }


    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
