package springboot.app.servicio;

import springboot.app.entities.Estudiante;
import springboot.app.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio implements Servicio<Estudiante> {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAll() throws Exception {
        return estudianteRepository.findAll();
    }

    public List<Estudiante> findAllWithOrder() throws Exception {
        return estudianteRepository.findAllWithOrder();
    }

    public Estudiante findByNroLibreta(int nroLibreta) throws Exception{
        return estudianteRepository.findByNroLibreta(nroLibreta);
    }

    public List<Estudiante> findByGenero(String genero) throws Exception{
        return estudianteRepository.findByGenero(genero);
    }

    public List<Estudiante> findByCarreraCiudad(int carreraId, String ciudad) throws Exception{
        return estudianteRepository.findByCarreraCiudad(carreraId, ciudad);
    }

    public Estudiante findByNroDocumento(int nroDocumento) throws Exception{
        return estudianteRepository.findByNroDocumento(nroDocumento);
    }

    @Override
    @Transactional
    public Estudiante findById(int id) throws Exception {
        try{
            Optional<Estudiante> estudiante = estudianteRepository.findById(id);
            return estudiante.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante save(Estudiante obj) throws Exception {
        try {
            return estudianteRepository.save(obj);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Estudiante update(int id, Estudiante obj) throws Exception {
        try{
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
            if(estudianteOptional.isPresent()){
                Estudiante estudiante = estudianteOptional.get();
                estudiante.setNombre(obj.getNombre());
                return estudianteRepository.save(estudiante);
            }
            return null;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
        try{
            if(estudianteRepository.existsById(id)){
                estudianteRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
