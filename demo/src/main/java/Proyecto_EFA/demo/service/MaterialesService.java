package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Materiales;
import Proyecto_EFA.demo.repository.MaterialesRepository;

@Service
@Transactional
public class MaterialesService {

    @Autowired
    private MaterialesRepository materialesRepository;

    public List<Materiales> getAllMateriales() {
        return materialesRepository.findAll();
    }

    public Materiales getMaterialById(Integer id) {
        return materialesRepository.findById(id).orElse(null);
    }

    public Materiales createMaterial(Materiales materiales) {
        return materialesRepository.save(materiales);
    }

    public Materiales updateMaterial(Integer id, Materiales materialesDetails) {
        Materiales materiales = materialesRepository.findById(id).orElse(null);
        if (materiales != null) {
            if (materialesDetails.getNombre() != null) {
                materiales.setNombre(materialesDetails.getNombre());
            }
            return materialesRepository.save(materiales);
        }
        return null;
    }

    public void deleteMaterial(Integer id) {
        materialesRepository.deleteById(id);
    }
}
