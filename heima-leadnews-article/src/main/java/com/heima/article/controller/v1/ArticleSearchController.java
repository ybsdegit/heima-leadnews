package com.heima.article.controller.v1;

import com.heima.article.apis.ArticleSearchControllerApi;
import com.heima.article.service.ApArticleSearchService;
import com.heima.model.article.dtos.UserSearchDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ArticleSearchController
 *
 * @author Paulson
 * @date 2020/2/15 15:27
 */

@RestController
@RequestMapping("/api/v1/article/search")
public class ArticleSearchController implements ArticleSearchControllerApi {

    @Autowired
    private ApArticleSearchService apArticleSearchService;

    @Override
    @PostMapping("/load_search_history")
    public ResponseResult findUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.findUserSearch(dto);
    }

    @Override
    @PostMapping("/del_search")
    public ResponseResult delUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.delUserSearch(dto);
    }

    @Override
    @PostMapping("/clear_search")
    public ResponseResult clearUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.clearUserSearch(dto);
    }

    @Override
    @PostMapping("/load_hot_keywords")
    public ResponseResult hotKeyWords(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.hotKeywords(dto.getHotDate());
    }

    @Override
    @PostMapping("/associate_search")
    public ResponseResult searchAssociate(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.searchAssociate(dto);
    }
}
