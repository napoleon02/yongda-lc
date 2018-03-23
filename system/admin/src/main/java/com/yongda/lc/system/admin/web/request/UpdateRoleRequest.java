package com.yongda.lc.system.admin.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @program: yongda-lc
 * @description: 编辑角色请求
 * @author: Napoleon
 * @create: 2018-03-23 09:48
 * @version: 0.0.1
 **/
@ApiModel
public class UpdateRoleRequest {

    @ApiModelProperty(value = "主键ID", required = true)
    @NotBlank(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "角色名称不能为空")
    @Length(min = 1, max = 20, message = "角色名称长度不符合规则")
    private String name;

    @ApiModelProperty(value = "角色说明", required = true)
    private String descr;

    @ApiModelProperty(value = "状态", required = true, allowableValues = "Y,N", readOnly = true)
    @NotBlank(message = "状态不能为空")
    private String isDisable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }
}
