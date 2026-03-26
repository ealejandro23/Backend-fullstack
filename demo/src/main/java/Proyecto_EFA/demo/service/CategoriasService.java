package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Categorias;
import Proyecto_EFA.demo.repository.CategoriasRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> findAll() {
        return categoriasRepository.findAll();
    }

    public Categorias findById(Integer id) {
        return categoriasRepository.findById(id).orElse(null);
    }

    public Categorias save(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    public Categorias partialUpdate(Categorias categorias) {
        Categorias existingCategoria = categoriasRepository.findById(categorias.getId()).orElse(null);
        if (existingCategoria != null) {
            if (categorias.getNombre() != null) {
                existingCategoria.setNombre(categorias.getNombre());
            }
            return categoriasRepository.save(existingCategoria);
        }
        return null;
    }

    public void deleteById(Integer id) {
        categoriasRepository.deleteById(id);
    }
}
