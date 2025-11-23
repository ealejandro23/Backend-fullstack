package Proyecto_EFA.demo.repository;

<<<<<<< HEAD
import Proyecto_EFA.demo.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    // Agrega este método faltante
    MetodoPago findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import Proyecto_EFA.demo.model.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {

}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
