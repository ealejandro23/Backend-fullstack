package Proyecto_EFA.demo.model;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
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
<<<<<<< HEAD
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
=======
    @GeneratedValue(strategy = GenerationType.AUTO)
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
<<<<<<< HEAD
    @JsonIgnoreProperties({"ventas", "hibernateLazyInitializer", "handler"})
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "estado_id")
<<<<<<< HEAD
    @JsonIgnoreProperties("ventas")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "metodo_pago_id")
<<<<<<< HEAD
    @JsonIgnoreProperties("ventas")
=======
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "metodo_envio_id")
<<<<<<< HEAD
    @JsonIgnoreProperties("ventas")
    private MetodoEnvio metodoEnvio;

    // ✅ ESTE CAMPO ES CRÍTICO - debe existir
    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductoVenta> items = new ArrayList<>();

    // ✅ Método para calcular el total automáticamente
    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        this.total = items.stream()
                .mapToDouble(item -> item.getSubtotal() != null ? item.getSubtotal() : 0)
                .sum();
    }
=======
    private MetodoEnvio metodoEnvio;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoVenta> items = new ArrayList<>();
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
}