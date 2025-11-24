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

<<<<<<< HEAD
    public MetodoEnvio getMetodoEnvioByNombre(String nombre) {
        return metodoEnvioRepository.findByNombre(nombre);
    }

    public MetodoEnvio createMetodoEnvio(MetodoEnvio metodoEnvio) {
        // Validar que el precio no sea negativo
        if (metodoEnvio.getPrecio() < 0) {
            throw new RuntimeException("El precio no puede ser negativo");
        }
        
        // Validar nombre único
        if (metodoEnvioRepository.existsByNombre(metodoEnvio.getNombre())) {
            throw new RuntimeException("Ya existe un método de envío con el nombre: " + metodoEnvio.getNombre());
        }
        
=======
    public MetodoEnvio createMetodoEnvio(MetodoEnvio metodoEnvio) {
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
        return metodoEnvioRepository.save(metodoEnvio);
    }

    public MetodoEnvio updateMetodoEnvio(Integer id, MetodoEnvio metodoEnvioDetails) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvio != null) {
<<<<<<< HEAD
            // Validar nombre único si se está cambiando
            if (metodoEnvioDetails.getNombre() != null && 
                !metodoEnvioDetails.getNombre().equals(metodoEnvio.getNombre())) {
                if (metodoEnvioRepository.existsByNombre(metodoEnvioDetails.getNombre())) {
                    throw new RuntimeException("Ya existe un método de envío con el nombre: " + metodoEnvioDetails.getNombre());
                }
                metodoEnvio.setNombre(metodoEnvioDetails.getNombre());
            }
            
            if (metodoEnvioDetails.getDescripcion() != null) {
                metodoEnvio.setDescripcion(metodoEnvioDetails.getDescripcion());
            }
            
            if (metodoEnvioDetails.getPrecio() != null) {
                if (metodoEnvioDetails.getPrecio() < 0) {
                    throw new RuntimeException("El precio no puede ser negativo");
                }
                metodoEnvio.setPrecio(metodoEnvioDetails.getPrecio());
            }
            
            return metodoEnvioRepository.save(metodoEnvio);
        }
        return null;
    }

    public MetodoEnvio partialUpdateMetodoEnvio(Integer id, MetodoEnvio metodoEnvioDetails) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvio != null) {
            if (metodoEnvioDetails.getNombre() != null) {
                // Validar nombre único
                if (!metodoEnvioDetails.getNombre().equals(metodoEnvio.getNombre()) &&
                    metodoEnvioRepository.existsByNombre(metodoEnvioDetails.getNombre())) {
                    throw new RuntimeException("Ya existe un método de envío con el nombre: " + metodoEnvioDetails.getNombre());
                }
                metodoEnvio.setNombre(metodoEnvioDetails.getNombre());
            }
            
            if (metodoEnvioDetails.getDescripcion() != null) {
                metodoEnvio.setDescripcion(metodoEnvioDetails.getDescripcion());
            }
            
            if (metodoEnvioDetails.getPrecio() != null) {
                if (metodoEnvioDetails.getPrecio() < 0) {
                    throw new RuntimeException("El precio no puede ser negativo");
                }
                metodoEnvio.setPrecio(metodoEnvioDetails.getPrecio());
            }
            
=======
            if (metodoEnvioDetails.getNombre() != null) {
                metodoEnvio.setNombre(metodoEnvioDetails.getNombre());
            }
            if (metodoEnvioDetails.getPrecio() != null) {
                metodoEnvio.setPrecio(metodoEnvioDetails.getPrecio());
            }
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
            return metodoEnvioRepository.save(metodoEnvio);
        }
        return null;
    }

    public void deleteMetodoEnvio(Integer id) {
<<<<<<< HEAD
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);
        if (metodoEnvio != null) {
            // Verificar si hay ventas asociadas antes de eliminar
            if (metodoEnvio.getVentas() != null && !metodoEnvio.getVentas().isEmpty()) {
                throw new RuntimeException("No se puede eliminar el método de envío porque tiene ventas asociadas");
            }
            metodoEnvioRepository.deleteById(id);
        }
    }

    public boolean existsById(Integer id) {
        return metodoEnvioRepository.existsById(id);
    }

}
=======
        metodoEnvioRepository.deleteById(id);
    }
}
>>>>>>> 0b8e625ef1bea8dfa064b2dc73c28c7f4393f2b9
