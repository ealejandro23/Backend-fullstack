package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.MetodoEnvio;
import Proyecto_EFA.demo.repository.MetodoEnvioRepository;

@Service
@Transactional
public class MetodoEnvioService {

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<MetodoEnvio> getAllMetodosEnvio() {
        return metodoEnvioRepository.findAll();
    }

    public MetodoEnvio getMetodoEnvioById(Integer id) {
        return metodoEnvioRepository.findById(id).orElse(null);
    }

    public MetodoEnvio createMetodoEnvio(MetodoEnvio metodoEnvio) {
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public MetodoEnvio updateMetodoEnvio(Integer id, MetodoEnvio metodoEnvioDetails) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvio != null) {
            if (metodoEnvioDetails.getNombre() != null) {
                metodoEnvio.setNombre(metodoEnvioDetails.getNombre());
            }
            if (metodoEnvioDetails.getPrecio() != null) {
                metodoEnvio.setPrecio(metodoEnvioDetails.getPrecio());
            }
            return metodoEnvioRepository.save(metodoEnvio);
        }
        return null;
    }

    public void deleteMetodoEnvio(Integer id) {
        metodoEnvioRepository.deleteById(id);
    }
}
