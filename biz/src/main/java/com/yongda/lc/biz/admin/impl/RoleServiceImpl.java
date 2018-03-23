package com.yongda.lc.biz.admin.impl;

import cn.hutool.core.util.StrUtil;
import com.yongda.lc.biz.admin.RoleService;
import com.yongda.lc.dal.mapper.RolesDOMapper;
import com.yongda.lc.dal.model.RolesDO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @program: yongda-lc
 * @description: 角色管理
 * @author: Napoleon
 * @create: 2018-03-22 19:52
 * @version: 0.0.1
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name = "rolesDOMapper")
    private RolesDOMapper rolesDOMapper;

    /**
     * @Description: 检查重复角色名称
     * @Param: [name-角色名,ignoreId-忽略的ID]
     * @return: boolean
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    @Override
    public boolean isRepeatName(String name, String ignoreId) {
        Example example = new Example(RolesDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);

        if (StrUtil.isNotBlank(ignoreId)) {
            criteria.andNotEqualTo("id", ignoreId);
        }
        int i = rolesDOMapper.selectCountByExample(example);
        return i > 0;
    }
}
