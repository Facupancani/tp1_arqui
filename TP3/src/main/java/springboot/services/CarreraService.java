package springboot.services;

import springboot.dto.CarreraConInscriptosDto;
import springboot.dto.ReporteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entities.Carrera;
import springboot.repositories.CarreraRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service("CarreraService")
public class CarreraService implements springboot.services.Service<Carrera> {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public List<CarreraConInscriptosDto> findCarrerasConInscriptos() throws Exception {
        return carreraRepository.findCarrerasConInscriptos();
    }

    public List<ReporteDto> generarReporte() throws Exception {
        return carreraRepository.generarReporte();
    }

    @Override
    @Transactional
    public Carrera findById(int id) throws Exception {
       try{
           Optional<Carrera> carrera = carreraRepository.findById(id);
           return carrera.get();
       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
    }

    @Override
    @Transactional
    public Carrera save(Carrera obj) throws Exception {
        try {
            return carreraRepository.save(obj);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Carrera update(int id, Carrera obj) throws Exception {
        try{
            Optional<Carrera> carreraOptional = carreraRepository.findById(id);
            if(carreraOptional.isPresent()){
                Carrera carrera = carreraOptional.get();
                carrera.setNombre(obj.getNombre());
                return carreraRepository.save(carrera);
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
            if(carreraRepository.existsById(id)){
                carreraRepository.deleteById(id);
                return true;
            }
            return false;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
