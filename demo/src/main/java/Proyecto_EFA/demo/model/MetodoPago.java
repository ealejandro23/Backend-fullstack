package Proyecto_EFA.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String nombre; // TARJETA_CREDITO, TRANSFERENCIA, EFECTIVO
    
    private String descripcion;
    
    @OneToMany(mappedBy = "metodoPago")
    private List<Venta> ventas = new ArrayList<>();
}