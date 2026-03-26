package Proyecto_EFA.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CloudinaryServiceTest {

    @Test
    void uploadImage_returnsMapWithUrlAndPublicId() throws Exception {
        CloudinaryService svc = new CloudinaryService();
        MockMultipartFile file = new MockMultipartFile("file", "img.png", "image/png", new byte[]{1,2,3});
        Map<String, Object> res = svc.uploadImage(file);
        assertNotNull(res);
        assertTrue(res.containsKey("url"));
        assertTrue(res.containsKey("public_id"));
    }
}
