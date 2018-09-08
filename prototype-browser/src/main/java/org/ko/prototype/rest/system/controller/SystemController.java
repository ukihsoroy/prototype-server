package org.ko.prototype.rest.system.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("system")
public class SystemController {


    @GetMapping("menu")
    public List getMenu () {
        return null;
    }

}
