package repositories;

import entities.Carrera;
import entities.Inscripcion;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class InscripcionRepository implements Repository<Inscripcion> {

    private EntityManager em;

    public InscripcionRepository(EntityManager em){
        this.em = em;
    }

    // ej 2b)
    // matricular un estudiante en una carrera
    @Override
    public void insert(Inscripcion obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.merge(obj);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar la inscripcion." + e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Inscripcion obj) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Inscripcion> findAll() {
        String query = "SELECT e FROM Inscripcion e";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Inscripcion findById(int id) {
        return em.find(Inscripcion.class, id);
    }

}
