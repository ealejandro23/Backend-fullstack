package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Estado;
import Proyecto_EFA.demo.repository.EstadoRepository;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> getAllEstados() {
        return estadoRepository.findAll();
    }

    public Estado getEstadoById(Integer id) {
        return estadoRepository.findById(id).orElse(null);
    }

    public Estado createEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado updateEstado(Integer id, Estado estadoDetails) {
        Estado estado = estadoRepository.findById(id).orElse(null);
        if (estado != null) {
            if (estadoDetails.getNombre() != null) {
                estado.setNombre(estadoDetails.getNombre());
            }
            return estadoRepository.save(estado);
        }
        return null;
    }

    public void deleteEstado(Integer id) {
        estadoRepository.deleteById(id);
    }
}
