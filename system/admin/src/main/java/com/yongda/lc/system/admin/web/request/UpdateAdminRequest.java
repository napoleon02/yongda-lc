package com.yongda.lc.system.admin.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改管理员信息请求模型
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-下午4:58
 */
@ApiModel
public class UpdateAdminRequest {

    @ApiModelProperty(value = "主键ID", required = true)
    @NotBlank(message = "主键ID不能为空")
    private String id;

    @ApiModelProperty(value = "状态", required = true, allowableValues = "Y,N", readOnly = true)
    @NotBlank(message = "状态不能为空")
    private String isDisable;

    @ApiModelProperty(value = "帐号", required = true)
    @NotBlank(message = "帐号不能为空")
    @Length(min = 3, max = 10, message = "帐号长度不符合规则")
    private String account;

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2, max = 10, message = "姓名长度不符合规则")
    private String name;

    @ApiModelProperty(value = "手机号码", required = true)
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号码不符合规则")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @Email(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不符合规则")
    private String email;

    @ApiModelProperty(value = "角色ID")
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
