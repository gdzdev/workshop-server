package org.gdzdev.workshop.backend.application.usecase;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.gdzdev.workshop.backend.domain.port.input.CloudinaryService;

import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    @Value("${cloudinary.default-image-url}")
    private String defaultImageUrl;

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        try {
            Map<?, ?> uploadOptions = ObjectUtils.asMap(
                    "folder", "products",
                    "unique_filename", true,
                    "overwrite", false,
                    "resource_type", "image",
                    "quality", "auto:best",
                    "width", 1600,
                    "height", 1600,
                    "crop", "limit",
                    "fetch_format", "auto"
            );
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new IOException("Error uploading image: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteImage(String imageUrl) throws IOException {
        if (shouldDeleteImage(imageUrl)) {
            String publicId = extractPublicIdFromUrl(imageUrl);
            if (publicId != null && publicId.startsWith("products/")) {
                try {
                    Map<?, ?> options = ObjectUtils.asMap(
                            "invalidate", true
                    );
                    cloudinary.uploader().destroy(publicId, options);
                } catch (Exception e) {
                    throw new IOException("Error deleting image. PublicId: " + publicId, e);
                }
            }
        }
    }

    @Override
    public boolean isSupportedImageType(String contentType) {
        return contentType != null &&
                (contentType.equals("image/jpeg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/webp") ||
                        contentType.equals("image/jpg"));
    }

    @Override
    public String getDefaultImageUrl() {
        return this.defaultImageUrl;
    }

    private boolean shouldDeleteImage(String imageUrl) {
        return imageUrl != null &&
                !imageUrl.isBlank() &&
                !imageUrl.equals(defaultImageUrl) &&
                imageUrl.contains("/products/");
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        try {
            Pattern pattern = Pattern.compile("upload/(?:v\\d+/)?(products/[^.]+)");
            Matcher matcher = pattern.matcher(imageUrl);
            return matcher.find() ? matcher.group(1) : null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URL format:" + imageUrl);
        }
    }
}
