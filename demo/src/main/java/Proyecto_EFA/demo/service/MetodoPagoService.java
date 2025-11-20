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

    public MetodoPago createMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public MetodoPago updateMetodoPago(Integer id, MetodoPago metodoPagoDetails) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElse(null);
        if (metodoPago != null) {
            if (metodoPagoDetails.getNombre() != null) {
                metodoPago.setNombre(metodoPagoDetails.getNombre());
            }
            if (metodoPagoDetails.getDescripcion() != null) {
                metodoPago.setDescripcion(metodoPagoDetails.getDescripcion());
            }
            return metodoPagoRepository.save(metodoPago);
        }
        return null;
    }

    public void deleteMetodoPago(Integer id) {
        metodoPagoRepository.deleteById(id);
    }
}
