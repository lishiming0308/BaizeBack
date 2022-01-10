package cn.hanwei.baize.baizeutil.dto;

import cn.hanwei.baize.baizeutil.JsonUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jingzhiqi
 * @date 2019/3/29 14:53
 * @desc 统一json格式，jsonResult的升级版，流式构建
 */
@Data
public class R implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static R ok(){
        R r = new R();
        r.code = 200;
        r.msg = "success";
        return r;
    }

    public static R error(){
        R r = new R();
        r.code = 500;
        r.msg = "error";
        return r;
    }

    public static R empty(){
        R r = new R();
        r.code = 404;
        r.msg = "no record";
        return r;
    }
    public  R code(int code){
        this.code = code;
        return this;
    }


    public  R data(Object data){
        this.data = data;
        return this;
    }

    public  R msg(String msg){
        this.msg = msg;
        return this;
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

}