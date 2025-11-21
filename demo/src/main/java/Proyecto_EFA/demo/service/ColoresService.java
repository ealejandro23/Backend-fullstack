package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Colores;
import Proyecto_EFA.demo.repository.ColoresRepository;

@Service
@Transactional
public class ColoresService {

    @Autowired
    private ColoresRepository coloresRepository;

    public List<Colores> getAllColores() {
        return coloresRepository.findAll();
    }

    public Colores getColorById(Integer id) {
        return coloresRepository.findById(id).orElse(null);
    }

    public Colores createColor(Colores colores) {
        return coloresRepository.save(colores);
    }

    public Colores updateColor(Integer id, Colores coloresDetails) {
        Colores color = coloresRepository.findById(id).orElse(null);
        if (color != null) {
            if (coloresDetails.getNombre() != null) {
                color.setNombre(coloresDetails.getNombre());
            }
            return coloresRepository.save(color);
        }
        return null;
    }

    public void deleteColor(Integer id) {
        coloresRepository.deleteById(id);
    }
}
