package com.yongda.lc.biz.admin;

import com.yongda.lc.support.support.web.response.ResponseWrapper;

/**
 * @program: yongda-lc
 * @description: 权限资源API
 * @author: Napoleon
 * @create: 2018-03-23 11:05
 * @version: 0.0.1
 **/
public interface RoleResourcesService {

    /**
     * @Description:递归查询系统所有权限[树结构]
     * @Param:[]
     * @return:ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/23
     */
    ResponseWrapper queryAllResources();

    /**
     * @Description:查询角色下拥有的资源
     * @Param:角色主键ID
     * @return:ResponseWrapper
     * @Author:Napoleon
     * @Date: 2018/3/23
     */
    ResponseWrapper queryResourceByRoleId(String roleId);
}
