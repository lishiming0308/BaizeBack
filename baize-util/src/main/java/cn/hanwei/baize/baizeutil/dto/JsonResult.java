package cn.hanwei.baize.baizeutil.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jing
 * 统一json结果
 */
@Data
public class JsonResult implements Serializable {
    private int code = 200; //200 success, 500 failure
    private String msg;
    private Object data;
}
