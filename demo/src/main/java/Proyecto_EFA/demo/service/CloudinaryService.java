package Proyecto_EFA.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    // Stub implementation used for tests and local runs where real Cloudinary is not configured.
    public Map<String, Object> uploadImage(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        result.put("url", "https://example.com/dummy.jpg");
        result.put("public_id", "dummy-id");
        return result;
    }
}
