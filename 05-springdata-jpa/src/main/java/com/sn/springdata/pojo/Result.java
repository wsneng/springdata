package com.sn.springdata.pojo;

import lombok.Data;

@Data
public class Result {
    private String result;
    private String resultMsg;

    public Result ok(){
        this.result = "01";
        this.resultMsg = "成功";
        return this;
    }
    public Result fail(){
        this.result = "02";
        this.resultMsg = "失败";
        return this;
    }
    public Result other(){
        this.result = "99";
        this.resultMsg = "其他";
        return this;
    }
    public Result paramNull(){
        this.result = "21";
        this.resultMsg = "必要的参数不能为空";
        return this;
    }
    public Result paramFormat(){
        this.result = "22";
        this.resultMsg = "参数格式错误";
        return this;
    }
    public Result dataVersion(){
        this.result = "23";
        this.resultMsg = "当时数据版本错误";
        return this;
    }
    public Result paramDataVersion(){
        this.result = "24";
        this.resultMsg = "参数之间的数据版本错误";
        return this;
    }
    public Result infoNoExit(){
        this.result = "25";
        this.resultMsg = "办件信息不存在";
        return this;
    }
    public Result SysException(){
        this.result = "25";
        this.resultMsg = "系统异常";
        return this;
    }
    public Result noReturn(){
        this.result = "25";
        this.resultMsg = "无返回值";
        return this;
    }
    public Result() {
    }

    public Result(String result, String resultMsg) {
        this.result = result;
        this.resultMsg = resultMsg;
    }
}
