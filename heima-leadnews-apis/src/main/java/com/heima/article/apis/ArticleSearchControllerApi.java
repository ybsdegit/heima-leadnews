package com.heima.article.apis;

import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * ArticleSearchControllerApi
 *
 * @author Paulson
 * @date 2020/2/15 15:26
 */
public interface ArticleSearchControllerApi {

    /**
     * 查询搜索历史
     * @param dto
     * @return
     */
    public ResponseResult findUserSearch(UserSearchDto dto);
}
