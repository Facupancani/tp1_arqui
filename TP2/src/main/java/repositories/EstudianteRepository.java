package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante> {

    private EntityManager em;

    public EstudianteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Estudiante obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(obj);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar estudiante." + e.getMessage());
            throw e;
        }
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
