package spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

/**
 * Created by frinder6 on 2016/12/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestControllerTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationConnect;

    @Before
    public void setUp() throws JsonProcessingException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();
    }

    @Test
    public void insert() throws Exception {
        String[] keys = new String[]{"key3", "key1", "key8"};
        String uri = "/test/add";
        for (int i = 0; i < 1; i++) {
            String responseStr = mvc.perform(MockMvcRequestBuilders
                    .post(uri, "json")
                    .accept(MediaType.APPLICATION_JSON)
                    .param("k", keys[new Random().nextInt(3)])
                    .param("v", "v1")
            )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
            System.out.println(responseStr);
        }
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findById() throws Exception {
        String uri = "/test/id";
        String responseStr = mvc.perform(MockMvcRequestBuilders
                .post(uri, "json")
                .accept(MediaType.APPLICATION_JSON)
                .param("key", "key8")
                .param("id", "1")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseStr);
    }

    @Test
    public void select() throws Exception {
        String uri = "/test/query";
        String responseStr = mvc.perform(MockMvcRequestBuilders
                .post(uri, "json")
                .accept(MediaType.APPLICATION_JSON)
                .param("key", "key3")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(responseStr);
    }

}