package cn.hanwei.baize.baizeutil;

import cn.hanwei.baize.baizeutil.dto.Code;
import cn.hanwei.baize.baizeutil.dto.JsonResult;
import cn.hanwei.baize.baizeutil.dto.PageDTO;
import cn.hanwei.baize.baizeutil.dto.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *@author jingzhiqi
 *@date 2018/3/20  9:14
 *@desc 系统工具类
 */

public class Tool {
    public static String randomUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String nowDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String nowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static Pageable getPageable(PageDTO dto){
        int page = dto.getPage() - 1;
        if (page < 0) {
            page = 0;
        }
        if (dto.getPageSize() == 0) {
            dto.setPageSize(1);
        }
        if (dto.getSort() != null && !dto.getSort().equals("")) {
            return PageRequest.of(page, dto.getPageSize(), Sort.by(new Sort.Order(Sort.Direction.fromString(dto.getOrder()), dto.getSort())));
        } else {
            return  PageRequest.of(page, dto.getPageSize());
        }
    }

    public static Pageable getPageable(PageDTO dto, Sort.Order... orders){
        int page = dto.getPage() - 1;
        if (page < 0) {
            page = 0;
        }
        if (dto.getPageSize() == 0) {
            dto.setPageSize(1);
        }
        return  PageRequest.of(page, dto.getPageSize(), Sort.by(orders));
    }
    public static Pageable getPageable(PageDTO dto, List<String> properties){
        int page = dto.getPage() - 1;
        if (page < 0) {
            page = 0;
        }
        if (dto.getPageSize() == 0) {
            dto.setPageSize(1);
        }
        List<Sort.Order> descList = new ArrayList<>();
        List<Sort.Order> ascList=new ArrayList<>();
        for (String property : properties) {
            descList.add(new Sort.Order(Sort.Direction.DESC,property));
            ascList.add(new Sort.Order(Sort.Direction.ASC,property));
        }
        if(dto.getOrder().equalsIgnoreCase("desc")){
            return  PageRequest.of(page, dto.getPageSize(), Sort.by(descList));
        }else if(dto.getOrder().equalsIgnoreCase("asc")){
            return  PageRequest.of(page, dto.getPageSize(), Sort.by(ascList));
        }else {
            return null;
        }

    }


    public static Predicate[] toArray(List<Predicate> pds){
        if (pds == null) {
            return null;
        }
        Predicate[] p = new Predicate[pds.size()];
        return pds.toArray(p);
    }

    public static <T> PageResult convertToPageResult(Page<T> t) {
        PageResult pr = new PageResult();
        long count =  t.getTotalElements();
        pr.setPageNow(t.getNumber() + 1);
        pr.setPageSize(t.getSize());
        pr.setTotal((int)count);
        if (count > 0)  {
            pr.setCode(Code.SUCCESS);
            pr.setData(t.getContent());
            pr.setMsg("success");
        } else {
            pr.setCode(Code.NO_RECORD);
            pr.setMsg("没有记录");
        }
        return pr;
    }

    public static PageResult getErrorPageResult() {
        PageResult pr = new PageResult();
        pr.setCode(Code.ERROR);
        pr.setMsg("内部服务错误");
        return pr;
    }

    public static  PageResult getErrorPageResult(String errorMsg) {
        PageResult pr = new PageResult();
        pr.setCode(Code.ERROR);
        pr.setMsg(errorMsg);
        return pr;
    }

    public static final JsonResult getSuccessJsonResult(){
        JsonResult pr = new JsonResult();
        pr.setCode(Code.SUCCESS);
        pr.setMsg("success");
        return pr;
    }

    public static final JsonResult getSuccessJsonResult(String msg){
        JsonResult pr = new JsonResult();
        pr.setCode(Code.SUCCESS);
        pr.setMsg(msg);
        return pr;
    }

    public static final JsonResult getSuccessJsonResult(Object obj, String msg){
        JsonResult pr = new JsonResult();
        pr.setCode(Code.SUCCESS);
        pr.setData(obj);
        pr.setMsg(msg);
        return pr;
    }

    public static final JsonResult getSuccessJsonResult(Object obj){
        JsonResult pr = new JsonResult();
        pr.setCode(Code.SUCCESS);
        pr.setData(obj);
        pr.setMsg("success");
        return pr;
    }

    public static  JsonResult getErrorJsonResult() {
        JsonResult pr = new JsonResult();
        pr.setCode(Code.ERROR);
        pr.setMsg("服务器内部错误");
        return pr;
    }


    public static JsonResult getErrorJsonResult(String errorMsg) {
        JsonResult pr = new JsonResult();
        pr.setCode(Code.ERROR);
        pr.setMsg(errorMsg);
        return pr;
    }

    public static JsonResult getJsonResult(int code, String msg) {
        JsonResult pr = new JsonResult();
        pr.setCode(code);
        pr.setMsg(msg);
        return pr;
    }


    public static String getString(Object obj){
        if(obj!=null){
            return obj.toString();
        }
        return "";
    }

    public static int getInt(Object obj){
        if(obj!=null){
            try{
                return Integer.parseInt(obj.toString());
            }catch(Exception e){
                return 0;
            }
        }
        return 0;
    }

    public static float getFloat(Object obj){
        if(obj!=null){
            try{
                return Float.parseFloat(obj.toString());
            }catch(Exception e){
                return 0;
            }
        }
        return 0;
    }

    public static double getDouble(Object obj){
        if(obj!=null){
            try{
                return Double.parseDouble(obj.toString());
            }catch(Exception e){
                return 0;
            }
        }
        return 0;
    }


    public static String getError(BindingResult result) {
        StringBuilder error = new StringBuilder();
        if (result.hasFieldErrors()) {
            List<FieldError> fieldErrors =   result.getFieldErrors();
            fieldErrors.forEach(e->{
                error.append(e.getField())
                        .append("->")
                        .append(e.getDefaultMessage())
                        .append("\r\n ");
            });
        }
        return error.toString();
    }

    public static String getErrorWithoutField(BindingResult result) {
        StringBuilder error = new StringBuilder();
        if (result.hasFieldErrors()) {
            List<FieldError> fieldErrors =   result.getFieldErrors();
            fieldErrors.forEach(e->{
                error.append(e.getDefaultMessage())
                        .append("\r\n ");
            });
        }
        return error.toString();
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
