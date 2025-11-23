package Proyecto_EFA.demo.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre; // PENDIENTE, PAGADO, ENVIADO, ENTREGADO
    
    @OneToMany(mappedBy = "estado")
    private List<Venta> ventas = new ArrayList<>();
}
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
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 40, nullable = false)
	private String nombre;

}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
