import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import dao.EstudianteDao;
import dao.CarreraDao;
import dao.InscripcionDao;
import entities.Carrera;
import entities.Estudiante;
import entities.Inscripcion;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("registroEstudiantes");
        EntityManager em = emf.createEntityManager();

        EstudianteDao estudianteDao = new EstudianteDao(em);
        CarreraDao carreraDao = new CarreraDao(em);
        InscripcionDao inscripcionDao = new InscripcionDao(em);

        // creo un estudiante
       /* Estudiante e1 = new Estudiante(62468, "Juan", "Gonzales", 22, "M", 43523742, "Buenos Aires");
        estudianteDao.insert(e1);

        // creo una carrera
        Carrera c1 = new Carrera(1, "Informatica");
        carreraDao.insert(c1);
*/
        Estudiante persistedEstudiante = estudianteDao.find(62468);
        Carrera persistedCarrera = carreraDao.find(1);

        // lo inscribo a una carrera
        Inscripcion i1 = new Inscripcion(persistedEstudiante, persistedCarrera, 2021, 2025, true);
        inscripcionDao.insert(i1);

        System.out.println(estudianteDao.findAll() +"\n");
        System.out.println(carreraDao.findAll()+"\n");
        System.out.println(inscripcionDao.findAll()+"\n");
    }

}
