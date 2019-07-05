package org.ko.sigma.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.sigma.core.bean.FileInfo;
import org.ko.sigma.core.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件服务")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService IFileService;

    @PostMapping
    @ApiOperation("上传文件")
    public FileInfo upload (MultipartFile file) throws IOException {
        return IFileService.upload(file);
    }

    @GetMapping("{id}")
    public void download (@PathVariable String id, ServletWebRequest request) {
        IFileService.download(id, request);
    }

}
