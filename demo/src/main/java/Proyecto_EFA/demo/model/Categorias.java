package Proyecto_EFA.demo.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
<<<<<<< HEAD
import jakarta.persistence.OneToMany;
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120)
    private String descripcion;

    @Column(name = "Nombre Categoria", length = 30, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

<<<<<<< HEAD
    @OneToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
}