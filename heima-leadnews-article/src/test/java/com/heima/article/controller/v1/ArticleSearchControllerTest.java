package com.heima.article.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.model.article.dtos.UserSearchDto;
import org.junit.Test;
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

/**
 * ArticleSearchControllerTest
 *
 * @author Paulson
 * @date 2020/2/15 15:45
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ArticleSearchControllerTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void testLoadArticleInfo() throws Exception {
        UserSearchDto dto = new UserSearchDto();
        dto.setEquipmentId(1);
        dto.setPageSize(20);
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/article/search/load_search_history")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(dto));
        mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

}
