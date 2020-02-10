package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleInfoDto;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * ArticleInfoControllerApi
 *
 * @author Paulson
 * @date 2020/2/10 22:10
 */

public interface ArticleInfoControllerApi {

    public ResponseResult loadArticleInfo(ArticleInfoDto dto);

}
