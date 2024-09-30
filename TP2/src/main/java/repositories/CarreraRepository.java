package repositories;

import entities.Carrera;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarreraRepository implements Repository<Carrera> {

    private EntityManager em;

    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Carrera obj) {
        em.persist(obj);
    }

    @Override
    public void update(Carrera obj) {

    }

    @Override
    public void delete(int id) {

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
