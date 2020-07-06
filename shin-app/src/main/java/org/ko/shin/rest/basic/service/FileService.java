package org.ko.shin.rest.basic.service;

import org.ko.shin.core.bean.FileInfo;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileInfo upload(MultipartFile file);

    void download(String id, String name, ServletWebRequest request);
}
