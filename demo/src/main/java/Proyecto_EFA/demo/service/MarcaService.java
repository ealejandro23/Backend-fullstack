package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Marca;
import Proyecto_EFA.demo.repository.MarcaRepository;

@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(Integer id) {
        return marcaRepository.findById(id).orElse(null);
    }

    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca updateMarca(Integer id, Marca marcaDetails) {
        Marca marca = marcaRepository.findById(id).orElse(null);
        if (marca != null) {
            if (marcaDetails.getNombre() != null) {
                marca.setNombre(marcaDetails.getNombre());
            }
            return marcaRepository.save(marca);
        }
        return null;
    }

    public void deleteMarca(Integer id) {
        marcaRepository.deleteById(id);
    }
}
