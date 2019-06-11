package org.ko.sigma.rest.menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.sigma.data.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mock;
    private List<Menu> menus = new ArrayList<>();

    @Before
    public void setup () {
        mock = MockMvcBuilders.webAppContextSetup(context).build();
        Menu parent = new Menu();
        parent.setName("系统操作");
        parent.setLink("/system");
        menus.add(parent);

        Menu child1 = new Menu();
        child1.setName("用户管理");
        child1.setParentId(1L);
        child1.setLink("/system/user");

        Menu child2 = new Menu();
        child2.setName("权限管理");
        child2.setParentId(1L);
        child2.setLink("/system/role");

        Menu child3 = new Menu();
        child3.setName("菜单管理");
        child3.setParentId(1L);
        child3.setLink("/system/menu");

        menus.add(child1);
        menus.add(child2);
        menus.add(child3);


    }

    @Test
    public void whenCreateSuccess () {
        menus.forEach(menu -> {
            try {
                String content = mapper.writeValueAsString(menu);
                String result = mock.perform(post("/sigma/menu")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(content))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void whenQuerySuccess () throws Exception {
        String result = mock.perform(get("/sigma/menu")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
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
    public void whenGetMenuByParentId () throws Exception {
        String result = mock.perform(get("/sigma/menu/child/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetInfoFail () throws Exception {
        mock.perform(get("/sigma/menu/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenUpdateSuccess () throws Exception {
        Menu menu = menus.get(0);
        menu.setName("system operation");
        String content = mapper.writeValueAsString(menu);
        String result = mock.perform(put("/sigma/menu/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess () throws Exception {
        mock.perform(delete("/sigma/menu/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("1"));
    }
}
