package com.yuntian.demo01.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserLoginLog)实体类
 *
 * @author makejava
 * @since 2020-01-16 21:17:32
 */
public class UserLoginLog implements Serializable {
    private static final long serialVersionUID = 185805353822096206L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 唯一id
    */
    private Long unId;
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 登录ip
    */
    private String ip;
    /**
    * mac地址
    */
    private String mac;
    /**
    * 登录时间
    */
    private Date loginTime;
    /**
    * 创建人
    */
    private Long createId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新人
    */
    private Long updateId;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 是否删除，0-未删除，1-删除，默认为0
    */
    private Integer isDelete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnId() {
        return unId;
    }

    public void setUnId(Long unId) {
        this.unId = unId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}