package springboot.app.servicio;

import springboot.app.entities.Carrera;
import springboot.app.dto.CarreraConInscriptosDto;
import springboot.app.dto.ReporteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.app.repositories.CarreraRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarreraServicio implements Servicio<Carrera> {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public List<CarreraConInscriptosDto> findCarrerasConInscriptos() throws Exception {
        List<Object[]> carreras = carreraRepository.findCarrerasConInscriptos();
        return carreras.stream()
                .map(carrera -> new CarreraConInscriptosDto(
                        (String) carrera[0],                       // String carrera
                        ((int) carrera[1])                          // Long inscriptos
                ))
                .collect(Collectors.toList());
    }

    public List<ReporteDto> generarReporte() throws Exception {
        List<Object[]> reportes = carreraRepository.generarReporte();
        return reportes.stream()
                .map(reporte -> new ReporteDto(
                        (String) reporte[0],                        // String nombreCarrera
                        ((int) reporte[1]),                         // Long anioInscripcion
                        ((Long) reporte[2]),                         // Long inscriptos
                        ((Long) reporte[3])                          // Long egresados
                ))
                .collect(Collectors.toList());
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
