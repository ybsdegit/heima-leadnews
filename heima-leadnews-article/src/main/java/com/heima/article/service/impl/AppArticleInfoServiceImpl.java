package com.heima.article.service.impl;

import com.heima.article.service.AppArticleInfoService;
import com.heima.model.article.pojos.ApArticleConfig;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.mappers.app.ApArticleConfigMapper;
import com.heima.model.mappers.app.ApArticleContentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * AppArticleInfoServiceImpl
 *
 * @author Paulson
 * @date 2020/2/10 22:01
 */
public class AppArticleInfoServiceImpl implements AppArticleInfoService {

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

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
            data.put("content", apArticleContent);
        }
        data.put("config", apArticleConfig);

        return ResponseResult.okResult(data);
    }
}
