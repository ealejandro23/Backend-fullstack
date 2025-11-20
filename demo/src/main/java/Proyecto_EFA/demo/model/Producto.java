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

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private Double precio;
    
    private Integer stock; // FALTABA ESTE CAMPO!
    
    private String codigo; // FALTABA ESTE CAMPO!

    @ManyToOne
    @JoinColumn(name = "tallas_id")
    private Tallas tallas; // PLURAL

    @ManyToOne
    @JoinColumn(name = "colores_id")
    private Colores colores; // PLURAL

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca; // SINGULAR - CORRECTO

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categorias categorias; // PLURAL

    @ManyToOne
    @JoinColumn(name = "materiales_id")
    private Materiales materiales; // PLURAL

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Imagen> imagenes = new ArrayList<>();
}