package Proyecto_EFA.demo.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private Cloudinary cloudinary;
    public String uploadImage(MultipartFile file) throws IOException {

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap("folder", "efa_productos")
        );
        return uploadResult.get("secure_url").toString();  
    }
    public Producto createProductoWithImage(Producto producto, MultipartFile imageFile) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadImage(imageFile);
            producto.setImagenUrl(imageUrl);
        }

        return productoRepository.save(producto);
    }

    // OBTENER TODOS LOS PRODUCTOS
    public Iterable<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // OBTENER POR ID
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    // ELIMINAR
    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    // ACTUALIZAR
    public Producto updateProducto(Integer id, Producto updated) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(updated.getNombre());
            p.setPrecio(updated.getPrecio());
            p.setDescripcion(updated.getDescripcion());
            p.setStock(updated.getStock());
            return productoRepository.save(p);
        }).orElse(null);
    }
}
