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

    /**
     * 删除搜索历史
     * @param dto
     * @return
     */
    ResponseResult delUserSearch(UserSearchDto dto);

    /**
     清空搜索历史
     @param dto
     @return
     */
    ResponseResult clearUserSearch(UserSearchDto dto);

    /**
     今日热词
     @return
     */
    ResponseResult hotKeywords(String date);

    /**
     * 联想词查询
     * 模糊查询
     * @param dto
     * @return
     */
    ResponseResult searchAssociate(UserSearchDto dto);
}
