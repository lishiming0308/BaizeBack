package cn.hanwei.baize.daqserver.controller;

import cn.hanwei.baize.baizeutil.Tool;
import cn.hanwei.baize.baizeutil.dto.LoginDTO;
import cn.hanwei.baize.baizeutil.dto.LoginVO;
import cn.hanwei.baize.baizeutil.dto.R;
import cn.hanwei.baize.daqserver.common.secure.AdminToken;
import cn.hanwei.baize.daqserver.common.secure.Constans;
import cn.hanwei.baize.daqserver.common.secure.MyMessageSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lishiming
 * @description: 后台认证-->控制器
 * @date 2022-1-7
 */
@Api(description = "平台管理-后台认证-控制器")
@RestController
@RequestMapping("/auth")
public class AutController {

    /**
     * @return com.hanwei.common.dto.R
     * @description: 认证状态
     * @date 2019-06-06 13:49
     */
    @ApiOperation(value = "查询认证状态")
    @PostMapping(value = "status")
    public R status(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
        return R.ok().data(authenticated);
    }
    /**
     * @return com.hanwei.common.dto.R
     * @description: 登录
     * @date 2019-06-05 16:18
     */
    @ApiOperation(value = "登录")
    @PostMapping(value = "login")
    public R login(@Validated @RequestBody LoginDTO dto, BindingResult result, HttpServletRequest req) {
        String errorMsg;
        Subject subject = SecurityUtils.getSubject();
        try {
            if (result.hasErrors()) {
                return R.error().data(subject.getSession().getAttribute(Constans.CHECK_CODE_BASE64)).msg(Tool.getError(result));
            }
            AdminToken adminToken=new AdminToken(dto.getUserName(), dto.getPassword(), dto.getCheckCode());
            subject.login(adminToken);
            LoginVO vo = new LoginVO();
            vo.setUserName(dto.getUserName());
            boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
            return R.ok().data(vo).msg(MyMessageSource.get("admin_login_success"));
        } catch (UnknownAccountException e) {
            errorMsg = MyMessageSource.get("admin_login_user_not_exist");
        } catch (IncorrectCredentialsException e) {
            errorMsg = MyMessageSource.get("admin_login_password_error");
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }
        return R.error().data(subject.getSession().getAttribute(Constans.CHECK_CODE_BASE64)).msg(errorMsg);
    }

}
