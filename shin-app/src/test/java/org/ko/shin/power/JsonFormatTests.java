package org.ko.shin.power;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonFormatTests {

    @Test
    public void whenFormatListSuccess () throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<Long> menuIds = new ArrayList<>();
        menuIds.add(1L);
        menuIds.add(2L);
        menuIds.add(3L);
        menuIds.add(4L);
        String content = mapper.writeValueAsString(menuIds);
        System.out.println(content);
    }
}
