package com.heima.behavior.service.impl;

import com.heima.behavior.service.AppUnLikesBehaviorService;
import com.heima.model.behavior.dtos.UnLikesBehaviorDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.behavior.pojos.ApUnlikesBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApUnlikesBehaviorMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * AppUnLikesBehaviorServiceImpl
 *
 * @author Paulson
 * @date 2020/2/12 22:43
 */

@Service
public class AppUnLikesBehaviorServiceImpl implements AppUnLikesBehaviorService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;

    @Override
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto) {

        // 获取用户信息，获取设备id
        ApUser user = AppThreadLocalUtils.getUser();

        // 根据当前的用户信息或设备id查询行为实体 ap_behavior_entry （保存了用户id 用户设备的id）
        if (user == null && dto.getEquipmentId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if (user != null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        if (apBehaviorEntry == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApUnlikesBehavior alb = new ApUnlikesBehavior();
        alb.setEntryId(apBehaviorEntry.getId());
        alb.setCreatedTime(new Date());
        alb.setArticleId(dto.getArticleId());
        alb.setType(dto.getType());

        int insert = apUnlikesBehaviorMapper.insert(alb);

        return ResponseResult.okResult(insert);
    }
}
