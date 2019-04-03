package org.ko.prototype.core.service;

import org.ko.prototype.core.bean.FileInfo;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    FileInfo upload(MultipartFile file);

    void download(String id, ServletWebRequest request);
}
