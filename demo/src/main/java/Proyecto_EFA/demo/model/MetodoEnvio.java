package Proyecto_EFA.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "metodo_envio")
public class MetodoEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String nombre; // DELIVERY, RETIRO_TIENDA
    
    private String descripcion;
    
    @Column(nullable = false)
    private Double precio = 0.0; // Precio del envío
    
    @OneToMany(mappedBy = "metodoEnvio")
    private List<Venta> ventas = new ArrayList<>();
}