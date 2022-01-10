package cn.hanwei.baize.daqserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String oid;

    @ApiModelProperty("序号")
    private Integer id;

    @ApiModelProperty("用户登录名称")
    private String code;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("是否是租户的默认用户")
    private Boolean isDefaultUser;

    @ApiModelProperty("是否是系统管理员")
    private Boolean isAdmin;

    @ApiModelProperty("是否是租户管理员")
    private Boolean isTenantAdmin;

    @ApiModelProperty("用户图片")
    private byte[] userImage;

    @ApiModelProperty("是否启用用户")
    private Boolean isEnable;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String mail;

    @ApiModelProperty("备注")
    private String remark;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getIsDefaultUser() {
        return isDefaultUser;
    }

    public void setIsDefaultUser(Boolean isDefaultUser) {
        this.isDefaultUser = isDefaultUser;
    }
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public Boolean getIsTenantAdmin() {
        return isTenantAdmin;
    }

    public void setIsTenantAdmin(Boolean isTenantAdmin) {
        this.isTenantAdmin = isTenantAdmin;
    }
    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
            "oid=" + oid +
            ", id=" + id +
            ", code=" + code +
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
