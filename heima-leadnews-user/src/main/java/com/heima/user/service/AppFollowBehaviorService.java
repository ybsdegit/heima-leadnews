package com.heima.user.service;

import com.heima.model.behavior.dtos.FollowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * AppFollowBehaviorService
 *
 * @author Paulson
 * @date 2020/2/11 23:08
 */
public interface AppFollowBehaviorService {
    /**
     * 存储关注行为数据
     */
    ResponseResult saveFollowBehavior(FollowBehaviorDto dto);
}
