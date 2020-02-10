package com.heima.article.service.impl;

import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("all")
public class AppArticleServiceImpl implements AppArticleService {

    private static final short MAX_PAGE_SIZE = 50;

    @Autowired
    private ApArticleMapper apArticleMapper;
    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;

    /**
     *
     * @param dto
     * @param type
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //  参数校验
        if (dto == null){
            dto = new ArticleHomeDto();
        }
        // 时间校验
        if (dto.getMaxBehotTime() == null){
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() == null){
            dto.setMinBehotTime(new Date());
        }

        // 分页校验
        Integer size = dto.getSize();
        if (size == null || size <= 0){
            size = 20;
        }

        size = Math.min(size, MAX_PAGE_SIZE);
        dto.setSize(size);

        // 文章频道参数校验
        if (StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }

        // 参数类型参数
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)){
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }

        //  获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();

        //  判断用户是否存在
        if (user != null){
            //  存在 已登录 加载推荐的文章
            return ResponseResult.okResult(getUserArticle(user, dto, type));
        }else {
            //  不存在 未登录 加载默认的文章
            return ResponseResult.okResult(getDefaultArticle(dto, type));
        }
    }

    /**
     * 加载默认的文章信息
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefaultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }


    /**
     * 先从用户的推荐表中查找文章信息，如果没有再从默认文章信息获取数据
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list = apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
        if(!list.isEmpty()){
            return apArticleMapper.loadArticleListByIdList(list);
        }else{
            return getDefaultArticle(dto,type);
        }
    }


}
