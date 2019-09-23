package com.hjm.service.impl;

import com.google.common.collect.Lists;
import com.hjm.service.IFileService;
import com.hjm.util.FTPUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author hjm
 */
@Service
public class FileService implements IFileService {
    @Override
    public String upload(MultipartFile file, String extName) {
//        File localFile = new File("upload", file.getOriginalFilename());
//        try {
//            FTPUtil.uploadFile(Lists.newArrayList(localFile));
//            return
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//            e.printStackTrace();
//        }
        return null;
    }
}
