package org.ko.prototype.rest.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class AdminUserControllerTests {

    @Autowired private WebApplicationContext context;

    private MockMvc mock;

    @Before
    public void setup () {
        mock = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess () throws Exception {
        String result = mock.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoSuccess () throws Exception {
        String result = mock.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail () throws Exception {
        mock.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess () throws Exception {
        String content = "{\"username\":\"Sultan\",\"password\":\"123456\",\"birthday\":" + new Date().getTime() + "}";
        System.out.println(new Date().getTime());
        String result = mock.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
//
//    @Test
//    public void whenUpdateSuccess () throws Exception {
//        Date time = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
////        Date time = new Date();
//        String content = "{\"id\":\"1\",\"username\":\"K.O\",\"password\":null,\"birthday\":" + time.getTime() + "}";
//        System.out.println(time.getTime());
//        String result = mock.perform(put("/user/1")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(content))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//    }
//
//    @Test
//    public void whenDeleteSuccess () throws Exception {
//        mock.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk());
//    }
}
