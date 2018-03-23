package com.yongda.lc.biz.admin.impl;

import cn.hutool.core.util.StrUtil;
import com.yongda.lc.biz.admin.RoleResourcesService;
import com.yongda.lc.dal.dto.ResourceDto;
import com.yongda.lc.dal.mapper.ResourceDOMapper;
import com.yongda.lc.dal.mapper.RoleResourceDOMapper;
import com.yongda.lc.dal.model.ResourceDO;
import com.yongda.lc.support.enums.YesOrNoEnum;
import com.yongda.lc.support.support.web.response.ResponseWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: yongda-lc
 * @description: 权限资源业务层
 * @author: Napoleon
 * @create: 2018-03-23 11:07
 * @version: 0.0.1
 **/
@Service("roleResourcesService")
public class RoleResourcesServiceImpl implements RoleResourcesService {

    @Resource(name = "resourceDOMapper")
    private ResourceDOMapper resourceDOMapper;

    @Resource(name = "roleResourceDOMapper")
    private RoleResourceDOMapper roleResourceDOMapper;

    @Override
    public ResponseWrapper queryAllResources() {
        List<ResourceDO> resourceDOS = this.getAllResource();
        List<ResourceDto> root = new ArrayList<>();
        //找到根节点
        for (ResourceDO resourceDO : resourceDOS) {
            if (StrUtil.isBlank(resourceDO.getPid())) {
                ResourceDto resourceDto = new ResourceDto();
                BeanUtils.copyProperties(resourceDO, resourceDto);
                //查找父节点下的子节点-递归
                this.recursiveTree(resourceDto, resourceDOS);
                root.add(resourceDto);
            }
        }

        return ResponseWrapper.ok().setData(root);

    }

    /*获取系统中所有可用资源*/
    public List<ResourceDO> getAllResource() {
        Example example = new Example(ResourceDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDisable", YesOrNoEnum.NO.getCode());
        example.setOrderByClause("sort desc");
        return resourceDOMapper.selectByExample(example);
    }

    @Override
    public ResponseWrapper queryResourceByRoleId(String roleId) {
        List<ResourceDO> allResource = this.getAllResource();
        List<ResourceDO> resourceDOS = roleResourceDOMapper.queryResourcesByRoleId(roleId);
        List<ResourceDto> roleUseResources = new ArrayList<>();
        for (ResourceDO resourceDO : resourceDOS) {
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resourceDO, resourceDto);
            roleUseResources.add(resourceDto);
        }
        for (ResourceDto resourceDto : roleUseResources) {
            this.recursiveTree(resourceDto, allResource);
        }
        return ResponseWrapper.ok().setData(roleUseResources);
    }

    /*递归调用树结构*/
    public ResourceDto recursiveTree(ResourceDto resourceDto, List<ResourceDO> resourceDOS) {
        List<ResourceDto> resourceDOS1 = new ArrayList<>();
        for (ResourceDO resourceDO1 : resourceDOS) {
            if (resourceDto.getId().equals(resourceDO1.getPid())) {
                ResourceDto resourceDto1 = new ResourceDto();
                BeanUtils.copyProperties(resourceDO1, resourceDto1);
                resourceDOS1.add(resourceDto1);
            }
        }
        resourceDto.setChilds(resourceDOS1);
        for (ResourceDto resourceDto1 : resourceDOS1) {
            recursiveTree(resourceDto1, resourceDOS);
        }
        return resourceDto;
    }

}
