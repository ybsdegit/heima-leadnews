package com.heima.article.service.impl;

import com.heima.article.service.ApArticleSearchService;
import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApBehaviorEntryMapper;
import com.heima.model.mappers.app.ApUserSearchMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserSearch;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ApArticleSearchServiceImpl
 *
 * @author Paulson
 * @date 2020/2/15 15:12
 */

@Service
public class ApArticleSearchServiceImpl implements ApArticleSearchService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;
    @Autowired
    private ApUserSearchMapper apUserSearchMapper;


    @Override
    public ResponseResult findUserSearch(UserSearchDto dto) {
        if (dto.getPageSize() > 50 || dto.getPageSize() < 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 先获取行为实体id
        ResponseResult result = getEntryId(dto);
        if (result.getCode() != AppHttpCodeEnum.SUCCESS.getCode()){
            return result;
        }
        // 查询搜索记录
        List<ApUserSearch> list = apUserSearchMapper.selectByEntryId((int) result.getData(), dto.getPageSize());
        return ResponseResult.okResult(list);

    }

    public ResponseResult getEntryId(UserSearchDto dto){
        ApUser user = AppThreadLocalUtils.getUser();
        // 用户和设备不能同时为空
        if(user == null && dto.getEquipmentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if(user!=null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(userId, dto.getEquipmentId());
        // 行为实体找以及注册了，逻辑上这里是必定有值得，除非参数错误
        if(apBehaviorEntry==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(apBehaviorEntry.getId());
    }


}
