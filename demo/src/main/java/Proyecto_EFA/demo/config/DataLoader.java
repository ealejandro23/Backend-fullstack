package Proyecto_EFA.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import Proyecto_EFA.demo.model.*;
import Proyecto_EFA.demo.repository.*;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriasRepository categoriasRepository;
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Autowired
    private TallasRepository tallasRepository;
    
    @Autowired
    private ColoresRepository coloresRepository;
    
    @Autowired
    private MaterialesRepository materialesRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriasRepository.count() == 0) {
            crearDatosBasicos();
        }

        if (productoRepository.count() == 0) {
            crearProductos();
            System.out.println(" Datos iniciales cargados: 20 productos con imágenes creados");
        }
    }

    private void crearDatosBasicos() {
        Categorias hombre = new Categorias();
        hombre.setNombre("hombre");
        categoriasRepository.save(hombre);

        Categorias mujer = new Categorias();
        mujer.setNombre("mujer");
        categoriasRepository.save(mujer);

        Categorias infantil = new Categorias();
        infantil.setNombre("infantil");
        categoriasRepository.save(infantil);

        Marca marcaDefault = new Marca();
        marcaDefault.setNombre("EFA");
        marcaRepository.save(marcaDefault);

        Tallas tallaDefault = new Tallas();
        tallaDefault.setNombre("Única");
        tallasRepository.save(tallaDefault);

        Colores colorDefault = new Colores();
        colorDefault.setNombre("Variado");
        coloresRepository.save(colorDefault);

        Materiales materialDefault = new Materiales();
        materialDefault.setNombre("Algodón");
        materialesRepository.save(materialDefault);
    }

    private void crearProductos() {
        Categorias hombre = categoriasRepository.findByNombre("hombre")
                .orElseThrow(() -> new RuntimeException("Categoría hombre no encontrada"));
        Categorias mujer = categoriasRepository.findByNombre("mujer")
                .orElseThrow(() -> new RuntimeException("Categoría mujer no encontrada"));
        Categorias infantil = categoriasRepository.findByNombre("infantil")
                .orElseThrow(() -> new RuntimeException("Categoría infantil no encontrada"));
        
        Marca marca = marcaRepository.findByNombre("EFA")
                .orElseThrow(() -> new RuntimeException("Marca EFA no encontrada"));
        
        Tallas talla = tallasRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Talla no encontrada"));
        
        Colores color = coloresRepository.findByNombre("Variado")
                .orElseThrow(() -> new RuntimeException("Color no encontrada"));
        
        Materiales material = materialesRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));

        // --- Ropa Hombre ---
        crearProducto("Polera básica blanca", "Polera básica de algodón 100%", 6990.0, 15, "POL-BLAN-001", hombre, marca, talla, color, material, "/img/5.webp");
        crearProducto("Polera oversize negra", "Polera oversize con corte moderno", 12990.0, 10, "POL-NEG-002", hombre, marca, talla, color, material, "/img/6.webp");
        crearProducto("Jeans Baggy Negro", "Jeans baggy de tendencia urbana", 10990.0, 8, "JEANS-BAG-003", hombre, marca, talla, color, material, "/img/baggy.webp");
        crearProducto("Jogger Morado", "Jogger deportivo color morado", 9990.0, 12, "JOG-MOR-004", hombre, marca, talla, color, material, "/img/morado.webp");
        crearProducto("Chaqueta jean clásica", "Chaqueta de jean clásica y versátil", 19990.0, 6, "CHAQ-JEAN-005", hombre, marca, talla, color, material, "/img/chaquetah.webp");
        crearProducto("Short AND1", "Short deportivo marca AND1", 15990.0, 20, "SHORT-AND1-006", hombre, marca, talla, color, material, "/img/and1.webp");
        crearProducto("Short AND1 Premium", "Short deportivo modelo premium AND1", 17990.0, 15, "SHORT-AND1-007", hombre, marca, talla, color, material, "/img/and.webp");

        // --- Ropa Mujer ---
        crearProducto("Polera deportiva Azul", "Polera deportiva color azul para mujer", 8990.0, 18, "POL-AZU-008", mujer, marca, talla, color, material, "/img/mujer3.webp");
        crearProducto("Polera oversize Roja", "Polera oversize roja de estilo urbano", 12990.0, 9, "POL-ROJ-009", mujer, marca, talla, color, material, "/img/mujer1.webp");
        crearProducto("Polera deportiva sin mangas", "Polera deportiva sin mangas para entrenamiento", 6990.0, 14, "POL-SIN-010", mujer, marca, talla, color, material, "/img/mujer2.webp");
        crearProducto("Pantalón cargo beige", "Pantalón cargo beige con múltiples bolsillos", 14990.0, 7, "PAN-CAR-011", mujer, marca, talla, color, material, "/img/pan1.webp");
        crearProducto("Pantalón skinny negro", "Pantalón skinny negro ajustado", 13990.0, 11, "PAN-SKI-012", mujer, marca, talla, color, material, "/img/pan2.webp");
        crearProducto("Chaqueta jean clásica", "Chaqueta de jean clásica para mujer", 19990.0, 8, "CHAQ-JEAN-013", mujer, marca, talla, color, material, "/img/chaqueta.webp");
        crearProducto("Chaqueta negra", "Chaqueta negra elegante para mujer", 15990.0, 10, "CHAQ-NEG-014", mujer, marca, talla, color, material, "/img/44.webp");
        crearProducto("Chaqueta deportiva", "Chaqueta deportiva para actividades al aire libre", 17990.0, 12, "CHAQ-DEP-015", mujer, marca, talla, color, material, "/img/mujer_chaqueta2.webp");

        // --- Ropa Infantil ---
        crearProducto("Polera infantil estampada", "Polera infantil con divertidos estampados", 7990.0, 25, "POL-INF-016", infantil, marca, talla, color, material, "/img/IN.webp");
        crearProducto("Polera con dibujos", "Polera infantil con coloridos dibujos", 8490.0, 20, "POL-DIB-017", infantil, marca, talla, color, material, "/img/infantil1.webp");
        crearProducto("Pantalón jeans infantil", "Pantalón jeans resistente para niños", 12990.0, 15, "PAN-JEI-018", infantil, marca, talla, color, material, "/img/infantil3.webp");
        crearProducto("Chaqueta infantil", "Chaqueta infantil abrigadora", 15990.0, 8, "CHAQ-INF-019", infantil, marca, talla, color, material, "/img/infantil4.webp");
        crearProducto("Chaqueta con capucha", "Chaqueta infantil con capucha incorporada", 17990.0, 10, "CHAQ-CAP-020", infantil, marca, talla, color, material, "/img/parka.webp");
    }

    private void crearProducto(String nombre, String descripcion, Double precio, Integer stock, 
                              String codigo, Categorias categoria, Marca marca, Tallas talla, 
                              Colores color, Materiales material, String imagenUrl) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCodigo(codigo);
        producto.setCategorias(categoria);
        producto.setMarca(marca);
        producto.setTallas(talla);
        producto.setColores(color);
        producto.setMateriales(material);
        producto.setImagenUrl(imagenUrl);
        
        productoRepository.save(producto);
    }
}