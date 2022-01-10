package cn.hanwei.baize.daqserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@Data
@ApiModel(value = "User对象", description = "用户")
@TableName(value = "\"User\"",autoResultMap = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="\"Oid\"")
    @ApiModelProperty("主键")
    private String oid;

    @TableField(value="\"Id\"")
    @ApiModelProperty("序号")
    private Integer id;

    @TableField(value="\"Code\"")
    @ApiModelProperty("用户登录名称")
    private String code;

    @TableField(value="\"Name\"")
    @ApiModelProperty("用户名称")
    private String name;

    @TableField(value="\"Password\"")
    @ApiModelProperty("密码")
    private String password;

    @TableField(value="\"IsDefaultUser\"")
    @ApiModelProperty("是否是租户的默认用户")
    private Boolean isDefaultUser;

    @TableField(value="\"IsAdmin\"")
    @ApiModelProperty("是否是系统管理员")
    private Boolean isAdmin;

    @TableField(value="\"IsTenantAdmin\"")
    @ApiModelProperty("是否是租户管理员")
    private Boolean isTenantAdmin;

    @TableField(value="\"UserImage\"")
    @ApiModelProperty("用户图片")
    private byte[] userImage;

    @TableField(value="\"IsEnable\"")
    @ApiModelProperty("是否启用用户")
    private Boolean isEnable;

    @TableField(value="\"CreateTime\"")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @TableField(value="\"UpdateTime\"")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @TableField(value="\"CreateUser\"")
    @ApiModelProperty("创建用户")
    private String createUser;

    @TableField(value="\"Phone\"")
    @ApiModelProperty("手机号")
    private String phone;

    @TableField(value="\"Mail\"")
    @ApiModelProperty("邮箱")
    private String mail;

    @TableField(value="\"Remark\"")
    @ApiModelProperty("备注")
    private String remark;


    @Override
    public String toString() {
        return "User{" +
            "oid=" + oid +
            ", Id=" + id +
            ", Code=" + code +
            ", name=" + name +
            ", password=" + password +
            ", isDefaultUser=" + isDefaultUser +
            ", isAdmin=" + isAdmin +
            ", isTenantAdmin=" + isTenantAdmin +
            ", userImage=" + userImage +
            ", isEnable=" + isEnable +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createUser=" + createUser +
            ", phone=" + phone +
            ", mail=" + mail +
            ", remark=" + remark +
        "}";
    }
}
