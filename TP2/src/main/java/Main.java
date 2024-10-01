import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.ReporteDto;
import entities.Carrera;
import helper.Helper;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.InscripcionRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // creo los entity managers
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroEstudiantes");
        EntityManager em = emf.createEntityManager();

        // creo los repositories
        CarreraRepository carreraRepository = new CarreraRepository(em);
        EstudianteRepository estudianteRepository = new EstudianteRepository(em);
        InscripcionRepository inscripcionRepository = new InscripcionRepository(em);

        // creo el helper y cargo las tables
        Helper helper = new Helper(em, carreraRepository, estudianteRepository, inscripcionRepository);
        helper.populateTables();

        /*
        System.out.println("\n========================================================\n");
        System.out.println("Estudiante repo:" + estudianteRepository.findAll());
        System.out.println("Carrera repo:" + carreraRepository.findAll());
        System.out.println("Inscripcion repo:" + inscripcionRepository.findAll());
        System.out.println("\n========================================================\n\n");
        System.out.println("Estudiante por ID: "+ estudianteRepository.findById(33865264));
        System.out.println("\n========================================================\n\n");
        System.out.println("Estudiantes por Genero: "+estudianteRepository.findByGenero("Male"));
        System.out.println("\n========================================================\n\n");
        System.out.println("Carreras con Estudiantes inscriptos: "+carreraRepository.findCarrerasConInscriptos());
        System.out.println("\n========================================================\n\n");
     */
        //unico metodo q no anda
        Carrera c = carreraRepository.findById(11);
        System.out.println("Estudiantes de una carrera determinada: "+estudianteRepository.findByCarreraCiudad(c,"Melekeok"));

/*

        System.out.println("\n\n============================== [REPORTES] ==============================");
        List<ReporteDto> reportes = carreraRepository.generarReporte();
        for(ReporteDto reporte : reportes){
            System.out.println(reporte);
        }
*/
        
        // cierro las conexiones a los entities managers
        em.close();
        emf.close();

    }

}
