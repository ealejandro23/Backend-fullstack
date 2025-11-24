package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.MetodoPago;
import Proyecto_EFA.demo.repository.MetodoPagoRepository;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago getMetodoPagoById(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

<<<<<<< HEAD
    public MetodoPago getMetodoPagoByNombre(String nombre) {
        return metodoPagoRepository.findByNombre(nombre);
    }

    public MetodoPago createMetodoPago(MetodoPago metodoPago) {
        // Validar nombre único
        if (metodoPagoRepository.existsByNombre(metodoPago.getNombre())) {
            throw new RuntimeException("Ya existe un método de pago con el nombre: " + metodoPago.getNombre());
        }
        
=======
    public MetodoPago createMetodoPago(MetodoPago metodoPago) {
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago updateMetodoPago(Integer id, MetodoPago metodoPagoDetails) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPago != null) {
<<<<<<< HEAD
            // Validar nombre único si se está cambiando
            if (metodoPagoDetails.getNombre() != null && 
                !metodoPagoDetails.getNombre().equals(metodoPago.getNombre())) {
                if (metodoPagoRepository.existsByNombre(metodoPagoDetails.getNombre())) {
                    throw new RuntimeException("Ya existe un método de pago con el nombre: " + metodoPagoDetails.getNombre());
                }
                metodoPago.setNombre(metodoPagoDetails.getNombre());
            }
            
            if (metodoPagoDetails.getDescripcion() != null) {
                metodoPago.setDescripcion(metodoPagoDetails.getDescripcion());
            }
            
            return metodoPagoRepository.save(metodoPago);
        }
        return null;
    }

    public MetodoPago partialUpdateMetodoPago(Integer id, MetodoPago metodoPagoDetails) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPago != null) {
            if (metodoPagoDetails.getNombre() != null) {
                // Validar nombre único
                if (!metodoPagoDetails.getNombre().equals(metodoPago.getNombre()) &&
                    metodoPagoRepository.existsByNombre(metodoPagoDetails.getNombre())) {
                    throw new RuntimeException("Ya existe un método de pago con el nombre: " + metodoPagoDetails.getNombre());
                }
                metodoPago.setNombre(metodoPagoDetails.getNombre());
            }
            
            if (metodoPagoDetails.getDescripcion() != null) {
                metodoPago.setDescripcion(metodoPagoDetails.getDescripcion());
            }
            
=======
            if (metodoPagoDetails.getNombre() != null) {
                metodoPago.setNombre(metodoPagoDetails.getNombre());
            }
            if (metodoPagoDetails.getDescripcion() != null) {
                metodoPago.setDescripcion(metodoPagoDetails.getDescripcion());
            }
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
            return metodoPagoRepository.save(metodoPago);
        }
        return null;
    }

    public void deleteMetodoPago(Integer id) {
<<<<<<< HEAD
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPago != null) {
            // Verificar si hay ventas asociadas antes de eliminar
            if (metodoPago.getVentas() != null && !metodoPago.getVentas().isEmpty()) {
                throw new RuntimeException("No se puede eliminar el método de pago porque tiene ventas asociadas");
            }
            metodoPagoRepository.deleteById(id);
        }
    }

    public boolean existsById(Integer id) {
        return metodoPagoRepository.existsById(id);
    }

}
=======
        metodoPagoRepository.deleteById(id);
    }
}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
