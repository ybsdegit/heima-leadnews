package com.heima.behavior.apis;

import com.heima.model.behavior.dtos.LikesBehaviorDto;
import com.heima.model.behavior.dtos.ShowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;

public interface BehaviorControllerApi {

    /**
     * 保存用户点击文章的行为
     * @param dto
     * @return
     */
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);

    /**
     * 保存点赞行为
     * @param dto
     * @return
     */
    ResponseResult saveLikesBehavior(LikesBehaviorDto dto);


}
