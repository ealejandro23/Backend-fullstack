package Proyecto_EFA.demo.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;

=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false)
	private String url;

	@ManyToOne
	@JoinColumn(name = "producto_id")
<<<<<<< HEAD
	@JsonIgnore
	private Producto producto;
}
=======
	private Producto producto;

}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
