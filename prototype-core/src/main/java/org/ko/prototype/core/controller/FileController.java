package org.ko.prototype.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.ko.prototype.core.bean.FileInfo;
import org.ko.prototype.core.properties.PrototypeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Date;

@Api(description = "文件服务")
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private PrototypeProperties prototypeProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @PostMapping
    @ApiOperation("上传文件")
    public FileInfo upload (MultipartFile file) throws IOException {
        LOGGER.info("file info name[{}], originalFileName[{}], size[{}]",
                file.getName(),
                file.getOriginalFilename(),
                file.getSize());

        File localFile = new File(prototypeProperties.getFile().getFolder(), new Date().getTime() + ".txt");
        file.transferTo(localFile);
//        file.getInputStream(); //上传至其他地方
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("{name}")
    public void download (@PathVariable String name, ServletWebRequest request) {

        try (
                InputStream inputStream =
                        new FileInputStream(new File(prototypeProperties.getFile().getFolder(),
                                new String(Base64.getDecoder().decode(name))));
                OutputStream outputStream = request.getResponse().getOutputStream()) {
            request.getResponse().setContentType("application/x-download");
            request.getResponse().addHeader("Content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
