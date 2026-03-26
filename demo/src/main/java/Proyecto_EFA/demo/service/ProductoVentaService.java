package Proyecto_EFA.demo.service;

import Proyecto_EFA.demo.model.ProductoVenta;
import Proyecto_EFA.demo.repository.ProductoVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoVentaService {
    
    @Autowired
    private ProductoVentaRepository productoVentaRepository;

    public List<ProductoVenta> getAllProductoVentas() {
        return productoVentaRepository.findAll();
    }
    
    public Optional<ProductoVenta> getProductoVentaById(Integer id) {
        return productoVentaRepository.findById(id);
    }
    
    public ProductoVenta createProductoVenta(ProductoVenta productoVenta) {
        return productoVentaRepository.save(productoVenta);
    }
    
    public ProductoVenta updateProductoVenta(Integer id, ProductoVenta productoVentaDetails) {
        Optional<ProductoVenta> existingProductoVenta = productoVentaRepository.findById(id);
        if (existingProductoVenta.isPresent()) {
            ProductoVenta pv = existingProductoVenta.get();
            if (productoVentaDetails.getProducto() != null) {
                pv.setProducto(productoVentaDetails.getProducto());
            }
            if (productoVentaDetails.getVenta() != null) {
                pv.setVenta(productoVentaDetails.getVenta());
            }
            if (productoVentaDetails.getCantidad() != null) {
                pv.setCantidad(productoVentaDetails.getCantidad());
            }
            if (productoVentaDetails.getPrecioUnitario() != null) {
                pv.setPrecioUnitario(productoVentaDetails.getPrecioUnitario());
            }
            return productoVentaRepository.save(pv);
        }
        return null;
    }
    
    public void deleteProductoVenta(Integer id) {
        productoVentaRepository.deleteById(id);
    }
    
    public List<ProductoVenta> getProductoVentasByVenta(Integer ventaId) {
        return productoVentaRepository.findByVentaId(ventaId);
    }
    
    public List<ProductoVenta> getProductoVentasByProducto(Integer productoId) {
        return productoVentaRepository.findByProductoId(productoId);
    }
    
    public ProductoVenta getProductoVentaByVentaAndProducto(Integer ventaId, Integer productoId) {
        return productoVentaRepository.findByVentaIdAndProductoId(ventaId, productoId);
    }

    public Integer getTotalQuantitySoldByProducto(Integer productoId) {
        Integer result = productoVentaRepository.getTotalQuantitySoldByProducto(productoId);
        return result != null ? result : 0;
    }
    
    public Double getTotalRevenueByProducto(Integer productoId) {
        Double result = productoVentaRepository.getTotalRevenueByProducto(productoId);
        return result != null ? result : 0.0;
    }
    
    public List<Object[]> getTop10SellingProducts() {
        return productoVentaRepository.findTop10SellingProducts();
    }
    
    public List<Object[]> getTop5SellingProducts() {
        return productoVentaRepository.findTop5SellingProducts();
    }
    
    public List<Object[]> getTopSellingProducts(int limit) {
        List<Object[]> allResults = productoVentaRepository.findAllSellingProducts();
        return allResults.stream().limit(limit).toList();
    }
    
    public Integer countItemsByVenta(Integer ventaId) {
        Integer result = productoVentaRepository.countItemsByVenta(ventaId);
        return result != null ? result : 0;
    }
}
