package org.ko.sigma.rest.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.sigma.data.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 权限测试
 */
@SpringBootTest
//用SpringRunner来运行测试用例
@RunWith(SpringRunner.class)
public class RoleControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;


    private MockMvc mock;
    private Role role;

    @Before
    public void setup () {
        mock = MockMvcBuilders.webAppContextSetup(context).build();
        role = new Role();
        role.setName("超级管理员");
    }

    @Test
    public void whenCreateSuccess () throws Exception {
        String content = mapper.writeValueAsString(role);
        System.out.println(new Date().getTime());
        String result = mock.perform(post("/role")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenQueryListSuccess () throws Exception {
        String result = mock.perform(get("/role")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenQueryInfoSuccess () throws Exception {
        String result = mock.perform(get("/role/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess () throws Exception {
        role.setName("super admin");
        String content = mapper.writeValueAsString(role);
        System.out.println(new Date().getTime());
        String result = mock.perform(put("/role/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess () throws Exception {
        System.out.println(new Date().getTime());
        String result = mock.perform(delete("/role/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenCreateRoleMenuSuccess () throws Exception {
        List<Long> menuIds = new ArrayList<>();
        menuIds.add(1L);
        menuIds.add(2L);
        menuIds.add(3L);
        menuIds.add(4L);
        String content = mapper.writeValueAsString(menuIds);
        String result = mock.perform(post("/role/1/menu")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

}
