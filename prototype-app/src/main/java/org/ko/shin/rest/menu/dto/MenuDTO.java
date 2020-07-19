package org.ko.prototype.rest.menu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.prototype.data.entity.Menu;
import org.ko.prototype.data.json.MenuMeta;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends Menu {

    private List<MenuDTO> children = new ArrayList<>();

    private MenuMeta meta;

}

