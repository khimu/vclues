package com.vclues.dto;

import java.util.List;

import com.vclues.core.entity.User;


public class AjaxResponseBody {

    String msg;
    List<User> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

}
