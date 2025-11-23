package Proyecto_EFA.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
<<<<<<< HEAD
        // Aceptar todas las rutas bajo /api/ para evitar bloqueos por rutas específicas
        registry.addMapping("/api/**")
                .allowedOrigins(
                    "http://localhost:3000",    // React dev server
                    "http://localhost:5173",    // Vite dev server  
                    "http://localhost:8081",    // Otro puerto local
                    "http://127.0.0.1:3000",    // Localhost IP
                    "http://127.0.0.1:5173",    // Localhost IP Vite
                    "http://127.0.0.1:5500",    // Live Server
                    "https://frontend-fullstack.onrender.com", // Tu frontend en producción
                    "https://frontend-jxjs.onrender.com"   // Otro dominio producción
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Type", "Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600); // 1 hora de cache para preflight
    }
}
=======
        registry.addMapping("/v1/**")
                .allowedOrigins(
                    "http://localhost:3000",
                    "http://localhost:5173",
                    "http://127.0.0.1:5500",
                    "https://frontend-fullstack.onrender.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
