package org.ko.sigma.rest.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.ko.sigma.rest.user.bean.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
//用SpringRunner来运行测试用例
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTests {

    @Autowired private WebApplicationContext context;
    @Autowired private ObjectMapper mapper;

    private MockMvc mock;
    private UserEntity userEntity;

    @Before
    public void setup () {
        mock = MockMvcBuilders.webAppContextSetup(context).build();
        userEntity = new UserEntity();
        userEntity.setUsername("sigma");
        userEntity.setBirthday(new Date());
        userEntity.setEmail("ko.shen@hotmail.com");
        userEntity.setNickname("K.O");
        userEntity.setPassword("tiger");
    }

    @Test
    public void whenCreateSuccess () throws Exception {
        String content = mapper.writeValueAsString(userEntity);
        System.out.println(new Date().getTime());
        String result = mock.perform(post("/sigma/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess () throws Exception {
        String result = mock.perform(get("/sigma/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail () throws Exception {
        mock.perform(get("/sigma/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenUpdateSuccess () throws Exception {
        Date time = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userEntity.setBirthday(time);
        String content = mapper.writeValueAsString(userEntity);
        String result = mock.perform(put("/sigma/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenQuerySuccess () throws Exception {
        String result = mock.perform(get("/sigma/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess () throws Exception {
        mock.perform(delete("/sigma/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("1"));
    }
}
