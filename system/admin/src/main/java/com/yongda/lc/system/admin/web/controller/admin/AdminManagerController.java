package com.yongda.lc.system.admin.web.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yongda.lc.biz.admin.AdminService;
import com.yongda.lc.dal.mapper.AdminDOMapper;
import com.yongda.lc.dal.model.AdminDO;
import com.yongda.lc.support.annotation.LoginRequire;
import com.yongda.lc.support.support.web.response.ResponseWrapper;
import com.yongda.lc.support.utils.PwdUtils;
import com.yongda.lc.system.admin.web.controller.BaseController;
import com.yongda.lc.system.admin.web.request.AddAdminRequest;
import com.yongda.lc.system.admin.web.request.QueryAdminRequest;
import com.yongda.lc.system.admin.web.request.UpdateAdminRequest;
import com.yongda.lc.system.admin.web.response.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Map;

/**
 * 系统管理员控制器
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-下午2:42
 */
@Controller
@RequestMapping(value = "/admin")
@Api(tags = "2.1.系统管理员接口", description = "2.系统管理员接口")
public class AdminManagerController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(AdminManagerController.class);

    @Resource(name = "adminDOMapper")
    private AdminDOMapper adminDOMapper;

    @Resource(name = "adminService")
    private AdminService adminService;

    /**
     * 分页查询管理员列表
     */
    @PostMapping(value = "/queryList")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "分页查询管理员列表")
    public ResponseWrapper queryListByPage(QueryAdminRequest request) {
        try {
            Example example = new Example(AdminDO.class);
            Example.Criteria criteria = example.createCriteria();

            if (StrUtil.isNotBlank(request.getStatus())) {
                criteria.andEqualTo("isDisable", request.getStatus().toUpperCase());
            }

            if (StrUtil.isNotBlank(request.getAccount())) {
                criteria.andEqualTo("account", "%" + request.getAccount() + "%");
            }

            if (StrUtil.isNotBlank(request.getName())) {
                criteria.andLike("name", "%" + request.getName() + "%");
            }

            if (StrUtil.isNotBlank(request.getPhone())) {
                criteria.andLike("phone", "%" + request.getPhone() + "%");
            }

            if (null != request.getStartDate()) {
                criteria.andGreaterThanOrEqualTo("createTime", DateUtil.beginOfDay(request.getStartDate()));
            }

            if (null != request.getEndDate()) {
                criteria.andLessThanOrEqualTo("createTime", DateUtil.endOfDay(request.getEndDate()));
            }

            PageInfo<Object> pageInfo = PageHelper
                    .startPage(request.getPageNum(), request.getPageSize())
                    .doSelectPageInfo(new ISelect() {
                        @Override
                        public void doSelect() {
                            adminDOMapper.selectByExample(example);
                        }
                    });

            Map<String, Object> data = new HashMap<>();
            data.put("rows", pageInfo.getList());
            data.put("total", pageInfo.getTotal());
            data.put("pageNum", pageInfo.getPageNum());
            data.put("pageSize", pageInfo.getPageSize());

            return ResponseWrapper.ok().setData(data);
        } catch (Exception e) {
            log.error("分页查询管理员列表异常：", e);
            return ResponseWrapper.error();
        }
    }

    /**
     * 添加管理员
     */
    @PostMapping(value = "/add")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "添加管理员")
    public ResponseWrapper add(@Valid AddAdminRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData(result.getFieldError().getDefaultMessage());
            }

            if (adminService.isRepetAccount(request.getAccount(), null)) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("帐号重复");
            }

            if (adminService.isRepetPhone(request.getPhone(), null)) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("手机号码重复");
            }

            if (StrUtil.isNotBlank(request.getEmail()) && adminService.isRepetEmail(request.getEmail(), null)) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("邮箱重复");
            }
            AdminDO adminDO = new AdminDO();
            BeanUtil.copyProperties(request, adminDO);
            adminDO.setOperId(getAdmin().getId());
            adminDO.setOperName(getAdmin().getName());
            adminService.add(adminDO);
            return ResponseWrapper.ok();
        } catch (Exception e) {
            log.error("添加管理员异常：", e);
            return ResponseWrapper.error();
        }
    }

    /**
     * 获取管理员信息
     */
    @PostMapping(value = "/get")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "获取管理员信息")
    public ResponseWrapper get(@ApiParam(value = "主键ID", required = true)
                               @RequestParam String id) {
        try {
            AdminDO adminDO = adminDOMapper.selectByPrimaryKey(id);
            return ResponseWrapper.ok().setData(adminDO);
        } catch (Exception e) {
            log.error("获取管理员信息异常：", e);
            return ResponseWrapper.error();
        }
    }

    /**
     * 修改管理员
     */
    @PostMapping(value = "/update")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "修改管理员")
    public ResponseWrapper update(@Valid UpdateAdminRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData(result.getFieldError().getDefaultMessage());
            }

            if (adminService.isRepetAccount(request.getAccount(), request.getId())) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("帐号重复");
            }

            if (adminService.isRepetPhone(request.getPhone(), request.getId())) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("手机号码重复");
            }

            if (StrUtil.isNotBlank(request.getEmail()) && adminService.isRepetEmail(request.getEmail(), request.getId())) {
                return ResponseWrapper.newInstance(ResponseCode.PARAMS_ERROR).setData("邮箱重复");
            }

            AdminDO adminDO = new AdminDO();
            BeanUtil.copyProperties(request, adminDO);
            adminDO.setOperId(getAdmin().getId());
            adminDO.setOperName(getAdmin().getName());
            adminDO.setUpdateTime(new Date());
            adminDOMapper.updateByPrimaryKeySelective(adminDO);
            return ResponseWrapper.ok();
        } catch (Exception e) {
            log.error("修改管理员信息异常：", e);
            return ResponseWrapper.error();
        }

    }

    /**
     * 重置管理员密码
     */
    @PostMapping(value = "/resetPwd")
    @LoginRequire
    @ResponseBody
    @ApiOperation(value = "重置管理员密码")
    public ResponseWrapper resetPwd(@ApiParam(value = "主键ID", required = true)
                                    @RequestParam String id) {
        try {
            String password = "123456";
            String uuid = RandomUtil.randomUUID();
            String newPwd = PwdUtils.buildPwd(password, uuid);
            AdminDO adminDO = new AdminDO();
            adminDO.setId(id);
            adminDO.setPassword(newPwd);
            adminDO.setSalt(uuid);
            adminDO.setOperId(getAdmin().getId());
            adminDO.setOperName(getAdmin().getName());
            adminDO.setUpdateTime(new Date());
            adminDOMapper.updateByPrimaryKeySelective(adminDO);
            return ResponseWrapper.ok();
        } catch (Exception e) {
            log.error("重置管理员密码异常：", e);
            return ResponseWrapper.error();
        }
    }
}
