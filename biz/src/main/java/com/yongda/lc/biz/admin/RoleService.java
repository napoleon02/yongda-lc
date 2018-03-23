package com.yongda.lc.biz.admin;

/**
 * @program: yongda-lc
 * @description: 角色API
 * @author: Napoleon
 * @create: 2018-03-22 19:51
 * @version: 0.0.1
 **/
public interface RoleService {

    /**
     * @Description: 检查重复角色名称
     * @Param: [name-角色名,ignoreId-忽略的ID]
     * @return: boolean
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    boolean isRepeatName(String name, String ignoreId);

}
