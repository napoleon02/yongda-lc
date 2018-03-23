package com.yongda.lc.biz.admin;

import com.yongda.lc.dal.model.AdminDO;

/**
 * 系统管理员业务接口
 * 作者：徐承恩
 * 邮箱：771247770@qq.com
 * 日期：2018/3/22-下午4:21
 */
public interface AdminService {

    /**
     * 手机号码是否重复
     *
     * @param phone    手机号码
     * @param ignoreId 忽略的ID
     * @return boolean
     */
    boolean isRepetPhone(String phone, String ignoreId);

    /**
     * 帐号是否重复
     *
     * @param account  帐号
     * @param ignoreId 忽略的ID
     * @return boolean
     */
    boolean isRepetAccount(String account, String ignoreId);

    /**
     * 邮箱是否重复
     *
     * @param email    邮箱
     * @param ignoreId 忽略的ID
     * @return boolean
     */
    boolean isRepetEmail(String email, String ignoreId);

    /**
     * 添加管理员
     *
     * @param adminDO 实体
     */
    void add(AdminDO adminDO);

    /**
     * @Description检查角色是否被引用
     * @Param:角色主键ID
     * @return:boolean
     * @Author:Napoleon
     * @Date: 2018/3/22
     */
    boolean isUsedRole(String roleId);

}
