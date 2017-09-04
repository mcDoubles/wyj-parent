package com.wyj.entity.data;

import com.wyj.entity.base.BaseEntity;

/**
 * 数据分组
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年8月23日 下午5:26:01
 */
public class DataGroup extends BaseEntity {
    private Long groupId;// 分组Id

    private String groupCode;// 分组编码

    private String groupName;// 分组名称

    private String remark;// 描述

    private Integer status;// 使用状态

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
