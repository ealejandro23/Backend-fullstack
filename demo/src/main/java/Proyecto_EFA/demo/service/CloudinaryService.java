package Proyecto_EFA.demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired(required = false)
    private Cloudinary cloudinary;

    public Map<?, ?> uploadImage(MultipartFile file) throws IOException {
        requireCloudinary();
        // La carpeta 'efa_productos' se usa para organizar las imágenes en Cloudinary.
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
            file.getBytes(), 
            ObjectUtils.asMap("folder", "efa_productos") 
        );
        return uploadResult;
    }
    public void deleteImage(String publicId) throws IOException {
        requireCloudinary();
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

    private void requireCloudinary() {
        if (cloudinary == null) {
            throw new IllegalStateException("Cloudinary no está configurado. Define CLOUDINARY_URL para subir imágenes.");
        }
    }
}
