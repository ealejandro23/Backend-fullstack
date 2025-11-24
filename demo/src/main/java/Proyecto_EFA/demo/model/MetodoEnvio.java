package Proyecto_EFA.demo.model;

<<<<<<< HEAD
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
=======
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MetodoEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120, nullable = false)
    private String nombre;

    private Double precio;
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
}