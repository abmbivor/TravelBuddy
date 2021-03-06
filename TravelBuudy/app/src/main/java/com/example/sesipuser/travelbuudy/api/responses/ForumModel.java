package com.example.sesipuser.travelbuudy.api.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SESIP User on 04-Dec-15.
 */
public class ForumModel {

    @SerializedName("QUESTION_NO")
    private String threadNo;

    @SerializedName("QUESTION_TEXT")
    private String text;

    @SerializedName("USER_NAME")
    private String username;

    @SerializedName("DATE_TIME")
    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getThreadNo() {
        return threadNo;
    }

    public void setThreadNo(String threadNo) {
        this.threadNo = threadNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
