package Proyecto_EFA.demo.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombreUsuario", length = 50, nullable = false)
    private String nombre;

    @Column(name = "correoUsuario", length = 50, nullable = false)
    private String correo;

    @Column(name = "contrasenaUsuario", length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rol_id")
<<<<<<< HEAD
    @JsonIgnoreProperties("usuarios")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
<<<<<<< HEAD
    @JsonIgnoreProperties("usuarios")
    private Direccion direccion;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Venta> ventas = new ArrayList<>();
}
=======
    private Direccion direccion;
}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
