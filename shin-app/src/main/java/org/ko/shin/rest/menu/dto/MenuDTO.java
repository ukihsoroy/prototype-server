package org.ko.shin.rest.menu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.shin.data.entity.Menu;
import org.ko.shin.data.json.MenuMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends Menu {

    private List<MenuDTO> children = new ArrayList<>();

    private MenuMeta meta;

}

