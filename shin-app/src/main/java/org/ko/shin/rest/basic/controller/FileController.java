package org.ko.shin.rest.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.shin.core.bean.FileInfo;
import org.ko.shin.rest.basic.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件上传下载")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    @ApiOperation("上传文件")
    public FileInfo upload (MultipartFile file) throws IOException {
        return fileService.upload(file);
    }

    @GetMapping("{id}")
    @ApiOperation("下载文件")
    public void download (@PathVariable String id,
                          @RequestParam(required = false) String name,
                          ServletWebRequest request) {
        fileService.download(id, name, request);
    }

}
