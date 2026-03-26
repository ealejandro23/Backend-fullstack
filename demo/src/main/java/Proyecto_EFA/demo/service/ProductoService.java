package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.Imagen; 
import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.repository.ProductoRepository;
import Proyecto_EFA.demo.repository.CategoriasRepository; 
import Proyecto_EFA.demo.dto.ProductoCreationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriasRepository categoriasRepository; 
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private ImagenService imagenService; 
    
    public Imagen addImageToProducto(Integer productoId, MultipartFile file) throws IOException {
        
        Optional<Producto> optionalProducto = productoRepository.findById(productoId);
        if (optionalProducto.isEmpty()) {
            throw new IOException("Producto no encontrado con ID: " + productoId);
        }
        
        Producto producto = optionalProducto.get();

        Map<?, ?> uploadResult = cloudinaryService.uploadImage(file);
        
        String imageUrl = uploadResult.get("url").toString();
        String publicId = uploadResult.get("public_id").toString(); 

        Imagen nuevaImagen = new Imagen();
        nuevaImagen.setUrl(imageUrl);
        nuevaImagen.setPublicId(publicId);
        nuevaImagen.setProducto(producto); 

        return imagenService.createImagen(nuevaImagen);
    }

    public List<Producto> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        productos.forEach(producto -> producto.getImagenes().size());
        return productos;
    }

    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).map(producto -> {
            producto.getImagenes().size(); 
            return producto;
        }).orElse(null);
    }
    
    public Producto createProducto(ProductoCreationDTO productoDto) {
        Producto nuevoProducto = new Producto();
        
        nuevoProducto.setNombre(productoDto.getNombre());
        nuevoProducto.setDescripcion(productoDto.getDescripcion());
        nuevoProducto.setPrecio(productoDto.getPrecio());
        nuevoProducto.setImagenUrl(productoDto.getImage()); 
        nuevoProducto.setStock(productoDto.getStock());
        
        if (productoDto.getCategoria() != null && !productoDto.getCategoria().isEmpty()) {
            String categoriaNombre = productoDto.getCategoria().substring(0, 1).toUpperCase() + productoDto.getCategoria().substring(1).toLowerCase();
            
            Optional<Categorias> optionalCategoria = categoriasRepository.findByNombre(categoriaNombre);
            
            if (optionalCategoria.isPresent()) {
                nuevoProducto.setCategorias(optionalCategoria.get()); 
            } else {
                System.err.println("Advertencia: Categoría no encontrada en la DB para el nombre: " + categoriaNombre);
            }
        }
        
        return productoRepository.save(nuevoProducto);
    }
    
    public Producto updateProducto(Integer id, Producto productoDetails) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setNombre(productoDetails.getNombre());
            producto.setPrecio(productoDetails.getPrecio());
            producto.setStock(productoDetails.getStock());
            producto.setCodigo(productoDetails.getCodigo());
            return productoRepository.save(producto);
        }
        return null;
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> findProductosByFilters(String categoria, String genero) { return List.of(); } 
    public List<Producto> getProductosByMarca(Integer marcaId) { return List.of(); }
    public List<Producto> getProductosByCategoria(Integer categoriaId) { return List.of(); }
    public List<Producto> getProductosByColor(Integer colorId) { return List.of(); }
    public List<Producto> getProductosByMaterial(Integer materialId) { return List.of(); }
    public List<Producto> getProductosByTalla(Integer tallaId) { return List.of(); }
    public List<Producto> getProductosByPriceRange(Double precioMin, Double PrecioMax) { return List.of(); }
    public List<Producto> searchProductosByNombre(String nombre) { return List.of(); }
    public List<Producto> getProductosByMarcaAndCategoria(Integer marcaId, Integer categoriaId) { return List.of(); }
    public List<Producto> getTop10MostExpensiveProducts() { return List.of(); }
    public List<Producto> getTop10CheapestProducts() { return List.of(); }
    public List<Producto> getProductosConStock() { return List.of(); }
    public Optional<Producto> getProductoByCodigo(String codigo) { return Optional.empty(); }
}
