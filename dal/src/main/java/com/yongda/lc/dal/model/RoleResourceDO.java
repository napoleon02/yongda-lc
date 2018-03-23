package com.yongda.lc.dal.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "T_ROLE_RESOURCE")
public class RoleResourceDO {
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "RESOURCE_ID")
    private String resourceId;

    /**
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * @return RESOURCE_ID
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}