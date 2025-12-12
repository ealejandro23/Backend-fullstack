package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Imagen;
import Proyecto_EFA.demo.repository.ImagenRepository;

@Service
@Transactional
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> getAllImagenes() {
        return imagenRepository.findAll();
    }

    public Imagen getImagenById(Integer id) {
        return imagenRepository.findById(id).orElse(null);
    }

    public Imagen createImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public Imagen updateImagen(Integer id, Imagen imagenDetails) {
        Imagen imagen = imagenRepository.findById(id).orElse(null);
        if (imagen != null) {
            if (imagenDetails.getUrl() != null) {
                imagen.setUrl(imagenDetails.getUrl());
            }
            if (imagenDetails.getProducto() != null) {
                imagen.setProducto(imagenDetails.getProducto());
            }
            return imagenRepository.save(imagen);
        }
        return null;
    }

    public void deleteImagen(Integer id) {
        imagenRepository.deleteById(id);
    }
}
