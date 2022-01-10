package cn.hanwei.baize.baizeutil.dto;

import lombok.Data;

/**
 *
 * @author jing
 * 带分页信息的json结果
 */
@Data
public class PageResult extends JsonResult{
    private int total;
    private int pageCount;
    private int pageNow;
    private int pageSize;


}