package com.api.ecoshieldwebservice.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
    String uploadImage(MultipartFile file);
}
