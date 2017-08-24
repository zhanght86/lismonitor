package com.sinosoft.support;

import org.springframework.stereotype.Component;

@Component
public class ResultObj {

    /** 结果标记 */
    private boolean flag = true;
    /** 处理结果信息 */
    private String msg;
    /** 处理对象 */
    private Object obj;

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
    public ResultObj() {

    }
    public ResultObj(boolean flag, String msg, Object obj) {
        super();
        this.flag = flag;
        this.msg = msg;
        this.obj = obj;
    }
    public ResultObj(boolean flag, String msg) {
        super();
        this.flag = flag;
        this.msg = msg;
    }
}
