package com.yongda.lc.dal.dto;

import com.yongda.lc.dal.model.AdminDO;

/**
 * @program: yongda-lc
 * @description: AdminDto
 * @author: Napoleon
 * @create: 2018-03-22 13:43
 * @version: 0.0.1
 **/
public class AdminDto extends AdminDO {

    /**
     * 最新操作时间开始
     */
    private String updateTimeStart;
    /**
     * 最新操作时间结束
     */
    private String updateTimeEnd;

    public String getUpdateTimeStart() {
        return updateTimeStart;
    }

    public void setUpdateTimeStart(String updateTimeStart) {
        this.updateTimeStart = updateTimeStart;
    }

    public String getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    public void setUpdateTimeEnd(String updateTimeEnd) {
        this.updateTimeEnd = updateTimeEnd;
    }
}
