package springboot.helper;

import springboot.entities.Carrera;
import springboot.entities.Estudiante;
import springboot.entities.Inscripcion;
import springboot.repositories.CarreraRepository;
import springboot.repositories.EstudianteRepository;
import springboot.repositories.InscripcionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargarDatos {

    private final CarreraRepository carreraRepository;
    private final EstudianteRepository estudianteRepository;
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    public CargarDatos(CarreraRepository cr, EstudianteRepository er, InscripcionRepository ir){
        this.carreraRepository = cr;
        this.estudianteRepository = er;
        this.inscripcionRepository = ir;
    }

    public void cargarDB() throws IOException {

        CSVParser parserCarrera = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/carreras.csv"));
        for (CSVRecord row : parserCarrera) {
            Carrera carrera = new Carrera(
                    Integer.parseInt(row.get("id_carrera")),
                    row.get("carrera"),
                    Integer.parseInt(row.get("duracion"))
            );
            carreraRepository.save(carrera);
        }

        CSVParser parserEstudiantes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudiantes.csv"));
        for (CSVRecord row : parserEstudiantes) {
            Estudiante estudiante = new Estudiante(
                    Integer.parseInt(row.get("DNI")),
                    row.get("nombre"),
                    row.get("apellido"),
                    Integer.parseInt(row.get("edad")),
                    row.get("genero"),
                    row.get("ciudad"),
                    Integer.parseInt(row.get("LU"))
            );
            estudianteRepository.save(estudiante);
        }

        CSVParser parserInscripciones = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/estudianteCarrera.csv"));
        for (CSVRecord row : parserInscripciones) {
            Inscripcion inscripcion = new Inscripcion(
                    Integer.parseInt(row.get("id")),
                    estudianteRepository.findByNroDocumento(Integer.parseInt(row.get("id_estudiante"))),
                    carreraRepository.findById(Integer.parseInt(row.get("id_carrera"))).orElseThrow(),
                    Integer.parseInt(row.get("inscripcion")),
                    Integer.parseInt(row.get("graduacion")),
                    Integer.parseInt(row.get("antiguedad"))
            );
            inscripcionRepository.save(inscripcion);
        }

    }

}
