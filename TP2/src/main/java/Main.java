import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import helper.Helper;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.InscripcionRepository;

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

        System.out.println("------------------");
        System.out.println("------------------");
        System.out.println("Estudiante repo:" + estudianteRepository.findAll());
        System.out.println("Carrera repo:" + carreraRepository.findAll());
        System.out.println("Inscripcion repo:" + inscripcionRepository.findAll());

        
        // cierro las conexiones a los entities managers
        em.close();
        emf.close();

    }

}
