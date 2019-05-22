package org.ko.sigma.core.service;

import org.ko.sigma.core.bean.FileInfo;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    FileInfo upload(MultipartFile file);

    void download(String id, ServletWebRequest request);
}
