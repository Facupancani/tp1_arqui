package springboot.app.servicio;

import jakarta.transaction.Transactional;
import springboot.app.entities.Carrera;
import springboot.app.entities.Estudiante;
import springboot.app.entities.Inscripcion;
import springboot.app.repositories.CarreraRepository;
import springboot.app.repositories.EstudianteRepository;
import springboot.app.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServicio implements Servicio<Inscripcion> {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    @Transactional
    public List<Inscripcion> findAll() throws Exception {
        return inscripcionRepository.findAll();
    }

    @Override
    @Transactional
    public Inscripcion findById(int id) throws Exception {
        try{
            Optional<Inscripcion> inscripcion = inscripcionRepository.findById(id);
            return inscripcion.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Inscripcion save(Inscripcion inscripcion) throws Exception {
        try {
            // si estudiante o carrera no existe throw exception
            Optional<Estudiante> estudiante = Optional.of(estudianteRepository.findById(inscripcion.getEstudiante().getNroDocumento())).orElseThrow();
            Optional<Carrera> carrera = Optional.of(carreraRepository.findById(inscripcion.getCarrera().getIdCarrera())).orElseThrow();
            inscripcion.setId(inscripcionRepository.findLastId()+1); // ??
            inscripcion.setEstudiante(estudiante.get());
            inscripcion.setCarrera(carrera.get());
            return inscripcionRepository.save(inscripcion);

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        try{
            if(inscripcionRepository.existsById(id)){
                inscripcionRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Inscripcion update(int id, Inscripcion obj) throws Exception {
        return null;
    }
}
