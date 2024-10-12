package springboot.services;

import springboot.entities.Inscripcion;
import springboot.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("InscripcionService")
public class InscripcionService implements springboot.services.Service<Inscripcion> {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Inscripcion findById(int id) throws Exception {
        return null;
    }

    @Override
    public Inscripcion save(Inscripcion obj) throws Exception {
        try {
            return inscripcionRepository.save(obj);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        return false;
    }

    @Override
    public Inscripcion update(int id, Inscripcion obj) throws Exception {
        return null;
    }
}
