package com.yongda.lc.system.admin.web.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yongda.lc.biz.admin.AdminService;
import com.yongda.lc.biz.admin.RoleService;
import com.yongda.lc.dal.mapper.RolesDOMapper;
import com.yongda.lc.dal.model.AdminDO;
import com.yongda.lc.dal.model.RolesDO;
import com.yongda.lc.support.annotation.LoginRequire;
import com.yongda.lc.support.enums.YesOrNoEnum;
import com.yongda.lc.support.support.web.response.ResponseWrapper;
import com.yongda.lc.system.admin.web.controller.BaseController;
import com.yongda.lc.system.admin.web.request.AddRoleRequest;
import com.yongda.lc.system.admin.web.request.QueryRolesRequest;
import com.yongda.lc.system.admin.web.request.UpdateRoleRequest;
import com.yongda.lc.system.admin.web.response.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yongda-lc
 * @description: 角色管理CONTROLLER
 * @author: Napoleon
 * @create: 2018-03-22 19:49
 * @version: 0.0.1
 **/
@Controller
@RequestMapping("/admin/role")
@Api(tags = "2.2.系统角色接口", description = "2.系统角色接口")
public class RoleManagerController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RoleManagerController.class);

    @Resource(name = "rolesDOMapper")
    private RolesDOMapper rolesDOMapper;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Resource(name = "adminService")
    private AdminService adminService;

    /**
     * @Description:角色列表查询
     * @Param: [queryRolesRequest-页面请求参数]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    @PostMapping("/queryRolesList")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "分页查询角色列表")
    public ResponseWrapper queryRolesList(QueryRolesRequest queryRolesRequest) {

        try {
            Example example = new Example(AdminDO.class);
            Example.Criteria criteria = example.createCriteria();
            if (StrUtil.isNotBlank(queryRolesRequest.getIsDisable())) {
                criteria.andEqualTo("isDisable", queryRolesRequest.getIsDisable());
            }
            if (StrUtil.isNotBlank(queryRolesRequest.getName())) {
                criteria.andEqualTo("name", queryRolesRequest.getName());
            }
            if (null != queryRolesRequest.getStartDate()) {
                criteria.andGreaterThanOrEqualTo("updateTime", DateUtil.beginOfDay(queryRolesRequest.getStartDate()));
            }

            if (null != queryRolesRequest.getEndDate()) {
                criteria.andLessThanOrEqualTo("updateTime", DateUtil.endOfDay(queryRolesRequest.getEndDate()));
            }

            Page<Object> objects = PageHelper
                    .startPage(queryRolesRequest.getPageNum(), queryRolesRequest.getPageSize())
                    .doSelectPage(new ISelect() {
                        @Override
                        public void doSelect() {
                            rolesDOMapper.selectByExample(example);
                        }
                    });
            Map<String, Object> map = new HashMap<>();
            map.put("pageNum", objects.getPageNum());
            map.put("pageSize", objects.getPageSize());
            map.put("rows", objects.getResult());
            map.put("total", objects.getTotal());
            return ResponseWrapper.ok().setData(map);
        } catch (Exception e) {
            logger.error("分页查询角色列表异常：", e);
            return ResponseWrapper.error();
        }

    }

    /**
     * @Description:查询系统所有角色[无分页]
     * @Param: [null]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/23
     */
    @PostMapping("/queryAllRoles")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "查询系统所有角色[无分页]")
    public ResponseWrapper queryAllRoles() {
        try {

            List<RolesDO> rolesDOS = rolesDOMapper.selectAll();
            return ResponseWrapper.ok().setData(rolesDOS);
        } catch (Exception e) {
            logger.error("查询系统所有角色[无分页]异常：", e);
            return ResponseWrapper.error();
        }
    }

    /**
     * @Description:增加角色
     * @Param: [addRoleRequest-角色增加请求参数]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    @PostMapping("/add")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "增加角色")
    public ResponseWrapper addRole(@Valid AddRoleRequest addRoleRequest, BindingResult result) {
        try {

            if (result.hasErrors()) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData(result.getFieldError().getDefaultMessage());
            }
            if (roleService.isRepeatName(addRoleRequest.getName(), null)) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("角色名称重复");
            }
            RolesDO rolesDO = new RolesDO();
            addRoleRequest.getIsDisable().toUpperCase();
            BeanUtils.copyProperties(addRoleRequest, rolesDO);
            AdminDO adminDO = getAdmin();
            rolesDO.setCreateId(adminDO.getId());
            rolesDO.setCreateTime(new Date());
            rolesDO.setCreateName(adminDO.getName());
            rolesDO.setUpdateId(adminDO.getId());
            rolesDO.setUpdateTime(new Date());
            rolesDO.setUpdateName(adminDO.getName());
            rolesDO.setUpdateTime(new Date());
            rolesDOMapper.insertSelective(rolesDO);
            return ResponseWrapper.ok();
        } catch (Exception e) {
            logger.error("增加角色异常：", e);
            return ResponseWrapper.error();
        }

    }

    /**
     * @Description:删除角色
     * @Param: [roleId-角色主键ID]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    @PostMapping("/delete")
    @ResponseBody
    @LoginRequire
    @ApiOperation(value = "删除角色")
    public ResponseWrapper deleteRole(@ApiParam(value = "角色主键ID", required = true)
                                      @RequestParam String roleId) {
        try {
            if (adminService.isUsedRole(roleId)) {
                return ResponseWrapper.other("该角色已经被引用,不能进行删除操作.");
            }
            RolesDO rolesDO = new RolesDO();
            rolesDO.setId(roleId);
            rolesDOMapper.deleteByPrimaryKey(rolesDO);
            return ResponseWrapper.ok();
        } catch (Exception e) {
            logger.error("删除角色异常：", e);
            return ResponseWrapper.error();
        }

    }

    /**
     * @Description:根据角色ID查询角色详情
     * @Param: [roleId-角色ID]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    @PostMapping("/selectOne")
    @ResponseBody
    @LoginRequire
    @ApiOperation(value = "根据角色ID查询角色详情")
    public ResponseWrapper selectOne(@ApiParam(value = "角色主键ID", required = true)
                                     @RequestParam String roleId) {

        try {
            RolesDO rolesDO = new RolesDO();
            rolesDO.setId(roleId);
            RolesDO rolesDO1 = rolesDOMapper.selectByPrimaryKey(rolesDO);
            return ResponseWrapper.ok().setData(rolesDO1);
        } catch (Exception e) {
            logger.error("根据角色ID查询角色详情异常：", e);
            return ResponseWrapper.error();
        }

    }

    /**
     * @Description:编辑角色
     * @Param: [updateRoleRequest-编辑角色请求]
     * @return: com.yongda.lc.support.support.web.response.ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/23
     */
    @PostMapping("/updateRole")
    @ResponseBody
    @LoginRequire
    @ApiOperation(value = "角色编辑")
    public ResponseWrapper updateRole(@Valid UpdateRoleRequest updateRoleRequest, BindingResult result) {
        try {

            if (result.hasErrors()) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData(result.getFieldError().getDefaultMessage());
            }
            RolesDO rolesDO = new RolesDO();
            rolesDO.setId(updateRoleRequest.getId());
            RolesDO rolesDO1 = rolesDOMapper.selectByPrimaryKey(rolesDO);
            if (!(YesOrNoEnum.YES.getCode().equals(rolesDO1.getIsDisable()))
                    && YesOrNoEnum.YES.getCode().equals(updateRoleRequest.getIsDisable().toUpperCase())) {
                if (adminService.isUsedRole(updateRoleRequest.getId())) {
                    return ResponseWrapper.other("该角色已经被引用,不能设置为无效状态.");
                }
            }
            if (roleService.isRepeatName(updateRoleRequest.getName(), updateRoleRequest.getId())) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("角色名称重复.");
            }
            RolesDO rolesDO2 = new RolesDO();
            BeanUtils.copyProperties(updateRoleRequest, rolesDO2);
            AdminDO adminDO = getAdmin();
            rolesDO2.setUpdateId(adminDO.getId());
            rolesDO2.setUpdateName(adminDO.getName());
            rolesDO2.setUpdateTime(new Date());
            rolesDOMapper.updateByPrimaryKeySelective(rolesDO2);
            return ResponseWrapper.ok();
        } catch (BeansException e) {
            logger.error("角色编辑异常：", e);
            return ResponseWrapper.error();
        }

    }


}
