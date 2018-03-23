package com.yongda.lc.biz.admin.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.yongda.lc.biz.admin.AdminService;
import com.yongda.lc.dal.mapper.AdminDOMapper;
import com.yongda.lc.dal.model.AdminDO;
import com.yongda.lc.support.utils.PwdUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 实现
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-下午4:21
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Resource(name = "adminDOMapper")
    private AdminDOMapper adminDOMapper;

    @Override
    public boolean isRepetPhone(String phone, String ignoreId) {
        Example example = new Example(AdminDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", phone);

        if (StrUtil.isNotBlank(ignoreId)) {
            criteria.andNotEqualTo("id", ignoreId);
        }

        int i = adminDOMapper.selectCountByExample(example);

        return i > 0;
    }

    @Override
    public boolean isRepetAccount(String account, String ignoreId) {
        Example example = new Example(AdminDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", account);

        if (StrUtil.isNotBlank(ignoreId)) {
            criteria.andNotEqualTo("id", ignoreId);
        }

        int i = adminDOMapper.selectCountByExample(example);

        return i > 0;
    }

    @Override
    public boolean isRepetEmail(String email, String ignoreId) {
        Example example = new Example(AdminDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);

        if (StrUtil.isNotBlank(ignoreId)) {
            criteria.andNotEqualTo("id", ignoreId);
        }

        int i = adminDOMapper.selectCountByExample(example);

        return i > 0;
    }

    @Override
    public void add(AdminDO adminDO) {
        String password = adminDO.getPassword();
        String uuid = RandomUtil.randomUUID();
        String newPwd = PwdUtils.buildPwd(password, uuid);
        adminDO.setPassword(newPwd);
        adminDO.setSalt(uuid);
        adminDO.setCreateTime(new Date());
        adminDO.setUpdateTime(new Date());
        adminDOMapper.insertSelective(adminDO);
    }

    @Override
    public boolean isUsedRole(String roleId) {
        Example example = new Example(AdminDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("role", roleId);
        int i = adminDOMapper.selectCountByExample(example);
        return i > 0;
    }

}
