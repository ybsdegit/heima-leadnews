package com.heima.model.mappers.app;

import com.heima.model.user.pojos.ApUserFollow;
import org.apache.ibatis.annotations.Param;

public interface ApUserFollowMapper {
    int insert(ApUserFollow record);
    int deleteByFollowId(@Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);
    ApUserFollow selectByFollowId(@Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);
}