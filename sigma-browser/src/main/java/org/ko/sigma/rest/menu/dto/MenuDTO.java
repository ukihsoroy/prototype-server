package org.ko.sigma.rest.menu.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.sigma.data.entity.Menu;
import org.ko.sigma.data.json.MenuMeta;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuDTO extends Menu {

    private List<MenuDTO> children;

    private MenuMeta menuMeta;


}

