package com.hjm.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author hjm
 */
public interface IFileService {
    String upload(MultipartFile file,String extName);
}
