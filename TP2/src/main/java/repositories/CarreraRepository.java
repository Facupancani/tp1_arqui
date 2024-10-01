package repositories;

import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class CarreraRepository implements Repository<Carrera> {

    private EntityManager em;

    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Carrera obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(obj);
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar Carrera." + e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Carrera obj) {

    }

    @Override
    public void delete(int id) {

    }

    public List<Carrera> findAll() {
        String query = "SELECT e FROM Carrera e";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Carrera findById(int id) {
        return em.find(Carrera.class, id);
    }

    public List<Carrera> findCarrerasConInscriptos(){
        String query = "SELECT c FROM Carrera c WHERE c.inscripciones.size > 0 ORDER BY c.inscripciones.size DESC";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

}
