package com.yongda.lc.dal.dto;

import com.yongda.lc.dal.model.ResourceDO;

import java.util.List;

/**
 * @program: yongda-lc
 * @description: 资源传输模型
 * @author: Napoleon
 * @create: 2018-03-23 13:27
 * @version: 0.0.1
 **/
public class ResourceDto extends ResourceDO {

    private List<ResourceDto> childs;

    public List<ResourceDto> getChilds() {
        return childs;
    }

    public void setChilds(List<ResourceDto> childs) {
        this.childs = childs;
    }
}
