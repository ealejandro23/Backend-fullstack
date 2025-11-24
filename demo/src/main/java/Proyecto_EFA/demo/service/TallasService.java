package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Tallas;
import Proyecto_EFA.demo.repository.TallasRepository;

@Service
@Transactional
public class TallasService {

    @Autowired
    private TallasRepository tallasRepository;

    public List<Tallas> getAllTallas() {
        return tallasRepository.findAll();
    }

    public Tallas getTallaById(Integer id) {
        return tallasRepository.findById(id).orElse(null);
    }

    public Tallas createTalla(Tallas tallas) {
        return tallasRepository.save(tallas);
    }

    public Tallas updateTalla(Integer id, Tallas tallaDetails) {
        Tallas tallas = tallasRepository.findById(id).orElse(null);
        if (tallas != null) {
            if (tallaDetails.getNombre() != null) {
                tallas.setNombre(tallaDetails.getNombre());
            }
            return tallasRepository.save(tallas);
        }
        return null;
    }

    public void deleteTalla(Integer id) {
        tallasRepository.deleteById(id);
    }
}
