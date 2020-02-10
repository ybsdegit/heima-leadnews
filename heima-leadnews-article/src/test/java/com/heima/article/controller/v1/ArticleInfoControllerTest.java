package com.heima.article.controller.v1;

import com.heima.article.ArticleJarApplication;
import com.heima.model.article.dtos.ArticleInfoDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

/**
 * ArticleInfoControllerTest
 *
 * @author Paulson
 * @date 2020/2/10 22:16
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ArticleInfoControllerTest {
    // 使用mockmvc测试
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;


    public void testLoadArticleInfo() throws Exception {

        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setArticleId(1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/article/load_article_info")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsBytes(articleInfoDto));

        mvc.perform(builder).andExpect(MockMvcResultMatchers
                .status()
                .isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
