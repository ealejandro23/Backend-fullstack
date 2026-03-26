package Proyecto_EFA.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import Proyecto_EFA.demo.model.Rol;
import Proyecto_EFA.demo.repository.RolRepository;

@Service
@Transactional
@SuppressWarnings("null")
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Rol getRolById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol updateRol(Integer id, Rol rolDetails) {
        Rol rol = rolRepository.findById(id).orElse(null);
        if (rol != null) {
            if (rolDetails.getNombre() != null) {
                rol.setNombre(rolDetails.getNombre());
            }
            return rolRepository.save(rol);
        }
        return null;
    }

    public void deleteRol(Integer id) {
        rolRepository.deleteById(id);
    }
}
