package cn.hanwei.baize.daqserver.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author lishiming
 * @since 2021-12-29
 */
@ApiModel(value = "Tenant对象", description = "租户")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String oid;

    @ApiModelProperty("序号")
    private Integer id;

    @ApiModelProperty("租户关联的数据库配置, 外键")
    private String databaseConfigOid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("租户图标文件")
    private byte[] logo;

    @ApiModelProperty("租户图标文件名称")
    private String logoName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否启用租户")
    private Boolean isEnable;

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
    public String getDatabaseConfigOid() {
        return databaseConfigOid;
    }

    public void setDatabaseConfigOid(String databaseConfigOid) {
        this.databaseConfigOid = databaseConfigOid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
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
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "Tenant{" +
            "oid=" + oid +
            ", id=" + id +
            ", databaseConfigOid=" + databaseConfigOid +
            ", name=" + name +
            ", logo=" + logo +
            ", logoName=" + logoName +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createUser=" + createUser +
            ", remark=" + remark +
            ", isEnable=" + isEnable +
        "}";
    }
}
