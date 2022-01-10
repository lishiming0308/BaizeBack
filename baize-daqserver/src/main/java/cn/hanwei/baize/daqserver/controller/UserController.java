package cn.hanwei.baize.daqserver.controller;


import cn.hanwei.baize.daqserver.entity.User;
import cn.hanwei.baize.daqserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@RestController
@RequestMapping("/daqserver/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String getSession= (String) request.getSession().getAttribute("sessionName");

        List<User> rtn= iUserService.getAllUser();
        return  rtn;
    }

    @GetMapping("/getUser/{id}")
    public User getUser( @PathVariable("id") String id){
        User rtn= iUserService.getUser(id);
        return  rtn;
    }

    @PostMapping("/addUser")
    public String addUser (@RequestBody User user){
        return iUserService.insert(user);
    }

    @PutMapping("/updateUser")
    public String updateUser (@RequestBody User user){

        return iUserService.update(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser (@PathVariable("id") String id){
        return iUserService.delete(id);
    }
}
