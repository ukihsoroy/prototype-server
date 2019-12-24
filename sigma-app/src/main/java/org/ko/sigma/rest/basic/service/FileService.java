package org.ko.sigma.rest.basic.service;

import org.ko.sigma.core.bean.FileInfo;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileInfo upload(MultipartFile file);

    void download(String id, String name, ServletWebRequest request);
}
