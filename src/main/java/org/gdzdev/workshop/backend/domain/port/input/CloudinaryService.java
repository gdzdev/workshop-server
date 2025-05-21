package org.gdzdev.workshop.backend.domain.port.input;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadImage(MultipartFile file) throws IOException;

    void deleteImage(String imageUrl) throws IOException;

    boolean isSupportedImageType(String contentType);

    String getDefaultImageUrl();
}
