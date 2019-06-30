package org.ko.sigma.core.service.impl;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.ko.sigma.core.bean.FileInfo;
import org.ko.sigma.core.exception.GeneralException;
import org.ko.sigma.core.properties.PrototypeProperties;
import org.ko.sigma.core.service.IFileService;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.core.util.BASE64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@Service
public class LocalFileService implements IFileService {

    @Autowired
    private PrototypeProperties prototypeProperties;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalFileService.class);

    /**
     * <p>文件上传</p>
     * @param file
     * @return
     */
    @Override
    public FileInfo upload(MultipartFile file) {
        LOGGER.info("file info name[{}], originalFileName[{}], size[{}]",
                file.getName(),
                file.getOriginalFilename(),
                file.getSize());
        String fileName = new Date().getTime() +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File localFile = new File(prototypeProperties.getFile().getFolder(), fileName);
        try {
            //从请求中将文件拷贝到本地
            file.transferTo(localFile);
            //上传至其他地方
//            file.getInputStream();
        } catch (IOException e) {
            LOGGER.error("org.ko.prototype.core.service.impl.LocalFileService#upload exception: {}", e);
        }
        return new FileInfo(BASE64.encryptBASE64(fileName.getBytes()), file.getOriginalFilename());
    }

    /**
     * <p>文件下载</p>
     * @param id
     * @param request
     */
    @Override
    public void download(String id, ServletWebRequest request) {
        String fileId = BASE64.decryptBASE64String(id);
        String filename = request.getParameter("filename");
        try (
                InputStream inputStream =
                        new FileInputStream(new File(prototypeProperties.getFile().getFolder(), fileId));
                OutputStream outputStream = request.getResponse().getOutputStream()) {
            request.getResponse().setContentType("application/x-download");
            request.getResponse().addHeader("Content-Disposition", "attachment;filename="
                    + (StringUtils.isNotBlank(filename) ? filename : fileId));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("org.ko.prototype.core.service.impl.LocalFileService#download exception: {}", e);
            throw new GeneralException(SystemCode.SYSTEM_ERROR);
        }
    }
}
