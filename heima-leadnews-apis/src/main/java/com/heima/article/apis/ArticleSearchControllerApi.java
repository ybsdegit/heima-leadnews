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

    /**
     * 删除历史记录
     * @param dto
     * @return
     */
    ResponseResult delUserSearch(UserSearchDto dto);

    /**
     * 清空历史记录
     * @param dto
     * @return
     */
    ResponseResult clearUserSearch(UserSearchDto dto);


    /**
     * 今日热词
     * @param dto
     * @return
     */
    ResponseResult hotKeyWords(UserSearchDto dto);

    /**
     * 联想词查询
     * @param dto
     * @return
     */
    ResponseResult searchAssociate(UserSearchDto dto);
}
