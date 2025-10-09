package com.api.ecoshieldwebservice.interfaces;

import com.api.ecoshieldwebservice.dtos.response.GeminiResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IGeminiService {
    GeminiResponseDTO analyze(MultipartFile image);
}
