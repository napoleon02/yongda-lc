package com.yongda.lc.dal.mapper;

import com.yongda.lc.dal.model.ResourceDO;
import com.yongda.lc.dal.model.RoleResourceDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleResourceDOMapper extends Mapper<RoleResourceDO> {

    List<ResourceDO> queryResourcesByRoleId(String roleId);
}