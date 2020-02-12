package com.heima.user.apis;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.UserRelationDto;

/**
 * UserRelationControllerApi
 *
 * @author Paulson
 * @date 2020/2/12 0:27
 */
public interface UserRelationControllerApi {

    /**
     * 关注或取消关注
     */
    ResponseResult follow(UserRelationDto dto);
}
