package cn.hanwei.baize.baizeutil.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据传输对象
 * @author jign
 *
 */
@Data
public class PageDTO implements Serializable {
    private int page=1;//当前页
    private int pageSize = 10;//一页大小
    private int start = 0;//当前页索引 由前台传值  如果前台不支持的话 自己计算
    private String sort; //排序字段
    private String order = "desc";//desc asc

}
