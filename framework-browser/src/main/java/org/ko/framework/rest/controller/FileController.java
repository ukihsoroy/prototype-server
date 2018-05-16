package org.ko.framework.rest.controller;


import org.apache.commons.io.IOUtils;
import org.ko.web.dto.FileInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("file")
public class FileController {
    String folder = "E:\\K.O\\java\\tutorials-java\\security\\security-web\\src\\main\\java\\org\\ko\\web\\controller";
    @PostMapping
    public FileInfo upload (MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("{id}")
    public void download (@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream out = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("content-Disposition", "attachment;filename=test.txt");
            IOUtils.copy(inputStream, out);
            out.flush();
        }
    }
}
