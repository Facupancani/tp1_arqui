package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante> {

    private EntityManager em;

    public EstudianteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Estudiante obj) {
        em.persist(obj);
    }

    @Override
    public void update(Estudiante obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Estudiante findById(int id) {
        return em.find(Estudiante.class, id);
    }

    public List<Estudiante> findAll() {
        String query = "SELECT e FROM Estudiante e";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    public List<Estudiante> findByCarreraCiudad(int idCarrera, String ciudad){
        String query = "SELECT e FROM Estudiante e JOIN Inscripcion i ON i.estudiante.nroLibreta = e.nroLibreta WHERE i.carrera.idCarrera = :idCarrera AND e.ciudadResidencia = :ciudadResidencia";
        Query q = em.createQuery(query);
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("ciudadResidencia", ciudad);
        return q.getResultList();
    }


}
