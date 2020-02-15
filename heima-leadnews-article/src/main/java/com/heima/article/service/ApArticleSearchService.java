package com.heima.article.service;

import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.common.dtos.ResponseResult;

/**
 * ApArticleSearchService
 *
 * @author Paulson
 * @date 2020/2/15 15:10
 */
public interface ApArticleSearchService {

    /**
     * 查询搜索厉害
     * @param dto
     * @return
     */
    ResponseResult findUserSearch(UserSearchDto dto);

}
