package com.heima.model.mappers.app;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApArticleMapper {
    /**
     * 照用户地理位置，加载文章
     * @param dto   参数封装对象
     * @param type  加载方向
     * @return
     */
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

    /**
     * 依据文章IDS来获取文章详细内容
     * @param list   文章ID
     * @return
     */
    List<ApArticle> loadArticleListByIdList(@Param("list") List<ApUserArticleList> list);

    ApArticle selectById(Long id);

}
