package Proyecto_EFA.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "precio_original")
    private Double precioOriginal;

    @Column(name = "oferta")
    private Boolean oferta;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private Double precio;
    
    private Integer stock;
    
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "tallas_id")
    private Tallas tallas;

    @ManyToOne
    @JoinColumn(name = "colores_id")
    private Colores colores;
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categorias categorias;

    @ManyToOne
    @JoinColumn(name = "materiales_id")
    private Materiales materiales;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Imagen> imagenes = new ArrayList<>();
}