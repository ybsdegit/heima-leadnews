package com.heima.article.service.impl;

import com.heima.article.service.AppArticleInfoService;
import com.heima.model.article.dtos.ArticleInfoDto;
import com.heima.model.article.pojos.ApArticleConfig;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.article.pojos.ApCollection;
import com.heima.model.behavior.dtos.FollowBehaviorDto;
import com.heima.model.behavior.pojos.ApBehaviorEntry;
import com.heima.model.behavior.pojos.ApLikesBehavior;
import com.heima.model.behavior.pojos.ApUnlikesBehavior;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.crawler.core.parse.ZipUtils;
import com.heima.model.mappers.app.*;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserFollow;
import com.heima.utils.common.BurstUtils;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * AppArticleInfoServiceImpl
 *
 * @author Paulson
 * @date 2020/2/10 22:01
 */
@Service
@Getter
public class AppArticleInfoServiceImpl implements AppArticleInfoService {

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApCollectionMapper apCollectionMapper;

    @Autowired
    private ApLikesBehaviorMapper apLikesBehaviorMapper;

    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;

    @Autowired
    private ApAuthorMapper apAuthorMapper;

    @Autowired
    private ApUserFollowMapper apUserFollowMapper;


    @Override
    public ResponseResult getArticleInfo(Integer articleId) {
        // 根据文章id查询config信息
        if (articleId == null || articleId < 1){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        Map<String, Object> data = new HashMap<>();

        // 判断当前文章是否删除
        ApArticleConfig apArticleConfig = apArticleConfigMapper.selectByArticleId(articleId);
        if (apArticleConfig == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }else if (!apArticleConfig.getIsDelete()){
            ApArticleContent apArticleContent = apArticleContentMapper.selectByArticleId(articleId);
            String content = ZipUtils.gunzip(apArticleContent.getContent());
            apArticleContent.setContent(content);
            data.put("content", apArticleContent);
        }
        data.put("config", apArticleConfig);

        return ResponseResult.okResult(data);
    }

    @Override
    public ResponseResult loadArticleBehavior(ArticleInfoDto dto) {
        ApUser user = AppThreadLocalUtils.getUser();
        // 用户和设备不能同时为空
        if (user==null && dto.getEquipmentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        Long userId = null;
        if (user != null){
            userId = user.getId();
        }
        ApBehaviorEntry apBehaviorEntry = apBehaviorEntryMapper.selectByUserIdOrEquipemntId(user.getId(), dto.getEquipmentId());
        // 行为实体找以及注册了，逻辑上这里是必定有值得，除非参数错误
        if(apBehaviorEntry==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        boolean isUnLike=false,isLike=false,isCollection=false,isFollow=false;
        String burst = BurstUtils.groudOne(apBehaviorEntry.getId());

        // 判断是否收藏
        ApCollection apCollection = apCollectionMapper.selectForEntryId(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if (apCollection != null){
            isCollection = true;
        }

        // 判断是否是已经喜欢
        ApLikesBehavior apLikesBehavior = apLikesBehaviorMapper.selectLastLike(burst, apBehaviorEntry.getId(), dto.getArticleId(), ApCollection.Type.ARTICLE.getCode());
        if (apLikesBehavior != null && apLikesBehavior.getOperation() == ApLikesBehavior.Operation.LIKE.getCode()){
            isLike = true;
        }

        // 判断是否是已经不喜欢
        ApUnlikesBehavior apUnlikesBehavior = apUnlikesBehaviorMapper.selectLastUnLike(apBehaviorEntry.getEntryId(), dto.getArticleId());
        if (apUnlikesBehavior != null && apUnlikesBehavior.getType() == ApUnlikesBehavior.Type.UNLIKE.getCode()){
            isUnLike = true;
        }

        // 判断是否关注
        ApAuthor apAuthor = apAuthorMapper.selectById(dto.getArticleId());
        if (user != null && apAuthor != null && apAuthor.getUserId() != null){
            ApUserFollow apUserFollow = apUserFollowMapper.selectByFollowId(BurstUtils.groudOne(user.getId()), user.getId(), apAuthor.getUserId().intValue());
            if (apUserFollow != null){
                isFollow = true;
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("isfollow",isFollow);
        data.put("islike",isLike);
        data.put("isunlike",isUnLike);
        data.put("iscollection",isCollection);
        return ResponseResult.okResult(data);
    }
}
