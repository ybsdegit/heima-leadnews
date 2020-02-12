package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApFollowBehavior;
import org.apache.ibatis.annotations.Param;

public interface ApFollowBehaviorMapper {
    int insert(ApFollowBehavior record);
    int deleteByFollowId(@Param("burst") String burst, @Param("userId") Long userId, @Param("followId") Integer followId);
}