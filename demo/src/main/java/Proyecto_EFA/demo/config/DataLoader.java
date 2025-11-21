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
            System.out.println("✅ Datos iniciales cargados: 20 productos creados");
        }
    }

    private void crearDatosBasicos() {
        // Crear categorías (sin descripción)
        Categorias hombre = new Categorias();
        hombre.setNombre("hombre");
        categoriasRepository.save(hombre);

        Categorias mujer = new Categorias();
        mujer.setNombre("mujer");
        categoriasRepository.save(mujer);

        Categorias infantil = new Categorias();
        infantil.setNombre("infantil");
        categoriasRepository.save(infantil);

        // Crear marca por defecto
        Marca marcaDefault = new Marca();
        marcaDefault.setNombre("EFA");
        marcaRepository.save(marcaDefault);

        // Crear talla por defecto (sin descripción)
        Tallas tallaDefault = new Tallas();
        tallaDefault.setNombre("Única");
        tallasRepository.save(tallaDefault);

        // Crear color por defecto (sin descripción)
        Colores colorDefault = new Colores();
        colorDefault.setNombre("Variado");
        coloresRepository.save(colorDefault);

        // Crear material por defecto (sin descripción)
        Materiales materialDefault = new Materiales();
        materialDefault.setNombre("Algodón");
        materialesRepository.save(materialDefault);
    }

    private void crearProductos() {
        // Obtener referencias
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

        // Crear productos (solo con campos que existen en tu modelo)
        crearProducto("Polera básica blanca", "Polera básica de algodón 100%", 6990.0, 15, "POL-BLAN-001", hombre, marca, talla, color, material);
        crearProducto("Polera oversize negra", "Polera oversize con corte moderno", 12990.0, 10, "POL-NEG-002", hombre, marca, talla, color, material);
        crearProducto("Jeans Baggy Negro", "Jeans baggy de tendencia urbana", 10990.0, 8, "JEANS-BAG-003", hombre, marca, talla, color, material);
        // ... agregar los otros productos
    }

    private void crearProducto(String nombre, String descripcion, Double precio, Integer stock, 
                              String codigo, Categorias categoria, Marca marca, Tallas talla, 
                              Colores color, Materiales material) {
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
        
        productoRepository.save(producto);
    }
}