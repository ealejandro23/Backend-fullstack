package Proyecto_EFA.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre; // PENDIENTE, PAGADO, ENVIADO, ENTREGADO
    
    @OneToMany(mappedBy = "estado")
    private List<Venta> ventas = new ArrayList<>();
}
