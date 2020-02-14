package com.heima.behavior.service;

import com.heima.model.behavior.dtos.ReadBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * AppReadBehaviorService
 *
 * @author Paulson
 * @date 2020/2/12 23:00
 */
public interface AppReadBehaviorService {
    /**
     * 存储阅读数据
     * @param dto
     * @return
     */
    public ResponseResult saveReadBehavior(ReadBehaviorDto dto);
}
