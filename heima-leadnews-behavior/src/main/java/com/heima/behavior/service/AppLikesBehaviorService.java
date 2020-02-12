package com.heima.behavior.service;

import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * AppLikesBehaviorService
 *
 * @author Paulson
 * @date 2020/2/12 22:22
 */
public interface AppLikesBehaviorService {

    /**
     * 保存喜欢行为
     * @param dto
     * @return
     */
    ResponseResult saveLikesBehavior(LikesBehaviorDto dto);
}
