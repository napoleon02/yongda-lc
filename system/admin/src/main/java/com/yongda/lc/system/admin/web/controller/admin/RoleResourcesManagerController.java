package com.yongda.lc.system.admin.web.controller.admin;

import com.yongda.lc.biz.admin.RoleResourcesService;
import com.yongda.lc.dal.mapper.ResourceDOMapper;
import com.yongda.lc.support.annotation.LoginRequire;
import com.yongda.lc.support.support.web.response.ResponseWrapper;
import com.yongda.lc.system.admin.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: yongda-lc
 * @description: 权限资源控制器
 * @author: Napoleon
 * @create: 2018-03-23 11:03
 * @version: 0.0.1
 **/
@Controller
@RequestMapping("/admin/roleorresources")
@Api(tags = "2.3.系统权限接口", description = "2.系统权限接口")
public class RoleResourcesManagerController extends BaseController {

    @Resource(name = "roleResourcesService")
    private RoleResourcesService roleResourcesService;

    @Resource(name = "resourceDOMapper")
    private ResourceDOMapper resourceDOMapper;

    /**
     * @Description:递归查询系统所有权限资源
     * @Param: [null]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/23
     */
    @PostMapping("/queryAllResources")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "查询系统所有权限资源")
    public ResponseWrapper queryAllResources() {
        try {
            return roleResourcesService.queryAllResources();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error();
        }
    }

    @PostMapping("/queryResourcesByRoleId")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "根绝角色ID查询权限资源[树结构]")
    public ResponseWrapper queryResourcesByRoleId(@ApiParam(value = "角色主键ID", required = true)
                                                  @RequestParam String roleId) {
        try {
            return roleResourcesService.queryResourceByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseWrapper.error();
        }
    }


}
