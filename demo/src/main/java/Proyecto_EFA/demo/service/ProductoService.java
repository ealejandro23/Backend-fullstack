package Proyecto_EFA.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import Proyecto_EFA.demo.model.Producto;
import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.repository.ProductoRepository;
import Proyecto_EFA.demo.repository.CategoriasRepository;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriasRepository categoriasRepository; 
    
    @Autowired
    private Cloudinary cloudinary; 

    // ----------------------------------------------------------------------
    // MÉTODOS DE CLOUDINARY
    // ----------------------------------------------------------------------
    
    public String uploadImage(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(
            file.getBytes(), 
            ObjectUtils.asMap("folder", "efa_productos") 
        );
        
        return uploadResult.get("url").toString();
    }
    
    public Producto createProductoWithImage(Producto producto, MultipartFile imageFile) throws IOException {
        
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = uploadImage(imageFile);
            producto.setImagenUrl(imageUrl); 
        }
        
        return productoRepository.save(producto);
    }

    // ----------------------------------------------------------------------
    // MÉTODOS CRUD Y FILTROS EXISTENTES
    // ----------------------------------------------------------------------

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> findProductosByFilters(String categoriaNombre, String generoNombre) {
        
        if (categoriaNombre != null && !categoriaNombre.isEmpty()) {            
            if (categoriaNombre.equalsIgnoreCase("todo") && generoNombre != null) {
                Optional<Categorias> generoOpt = categoriasRepository.findByNombre(generoNombre);
                
                if (generoOpt.isPresent()) {
                    return productoRepository.findByCategoriaId(generoOpt.get().getId());
                } else {
                    return List.of();
                }
            }
            else {
                Optional<Categorias> categoriaOpt = categoriasRepository.findByNombre(categoriaNombre);
                
                if (categoriaOpt.isPresent()) {
                    Integer categoriaId = categoriaOpt.get().getId();
                    return productoRepository.findByCategoriaId(categoriaId);
                } else {
                    return List.of();
                }
            }
        }
        if (generoNombre != null && !generoNombre.isEmpty()) {
            Optional<Categorias> generoOpt = categoriasRepository.findByNombre(generoNombre);
            
            if (generoOpt.isPresent()) {
                Integer generoId = generoOpt.get().getId();
                return productoRepository.findByCategoriaId(generoId);
            } else {
                return List.of();
            }
        }
        
        return productoRepository.findAll();
    }
    
    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Integer id, Producto productoDetails) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            
            if (productoDetails.getNombre() != null) {
                producto.setNombre(productoDetails.getNombre());
            }
            if (productoDetails.getDescripcion() != null) {
                producto.setDescripcion(productoDetails.getDescripcion());
            }
            if (productoDetails.getPrecio() != null) {
                producto.setPrecio(productoDetails.getPrecio());
            }
            if (productoDetails.getStock() != null) {
                producto.setStock(productoDetails.getStock());
            }
            if (productoDetails.getMarca() != null) {
                producto.setMarca(productoDetails.getMarca());
            }
            if (productoDetails.getCategorias() != null) {
                producto.setCategorias(productoDetails.getCategorias());
            }
            if (productoDetails.getColores() != null) {
                producto.setColores(productoDetails.getColores());
            }
            if (productoDetails.getMateriales() != null) {
                producto.setMateriales(productoDetails.getMateriales());
            }
            if (productoDetails.getTallas() != null) {
                producto.setTallas(productoDetails.getTallas());
            }
            if (productoDetails.getImagenes() != null){
                producto.setImagenes(productoDetails.getImagenes());
            }
            if (productoDetails.getImagenUrl() != null) {
                producto.setImagenUrl(productoDetails.getImagenUrl());
            }
            if (productoDetails.getCodigo() != null) {
                producto.setCodigo(productoDetails.getCodigo());
            }
            return productoRepository.save(producto);
        }
        return null;
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
    
    public List<Producto> getProductosByMarca(Integer marcaId) {
        return productoRepository.findByMarcaId(marcaId);
    }
    
    public List<Producto> getProductosByCategoria(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    public List<Producto> getProductosByPriceRange(Double precioMin, Double precioMax) {
        return productoRepository.findByPriceRange(precioMin, precioMax);
    }
    
    public List<Producto> searchProductosByNombre(String nombre) {
        return productoRepository.searchByNombre(nombre);
    }
    
    public List<Producto> getProductosByColor(Integer colorId) {
        return productoRepository.findByColorId(colorId);
    }
    
    public List<Producto> getProductosByMaterial(Integer materialId) {
        return productoRepository.findByMaterialId(materialId);
    }
    
    public List<Producto> getProductosByTalla(Integer tallaId) {
        return productoRepository.findByTallaId(tallaId);
    }
    
    public List<Producto> getProductosByMarcaAndCategoria(Integer marcaId, Integer categoriaId) {
        return productoRepository.findByMarcaIdAndCategoriaId(marcaId, categoriaId);
    }

    public List<Producto> getTop10MostExpensiveProducts() {
        return productoRepository.findTop10ByOrderByPrecioDesc();
    }
    
    public List<Producto> getTop10CheapestProducts() {
        return productoRepository.findTop10ByOrderByPrecioAsc();
    }

    public List<Producto> searchByNombreContaining(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<Producto> getProductosConStock() {
        return productoRepository.findByStockGreaterThan(0);
    }
    
    public Optional<Producto> getProductoByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }
}
