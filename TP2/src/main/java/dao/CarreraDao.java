package dao;

import entities.Carrera;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarreraDao {

    private EntityManager em;

    public CarreraDao(EntityManager em) {
        this.em = em;
    }

    public void insert(Carrera carrera){
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    public Carrera find(int id) {
        return em.find(Carrera.class, id);
    }

    public List<Carrera> findAll(){
        return em.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
    }

    // ej 2f)
    public List<Carrera> listarCarrerasPorEstudiantesInscriptos() {
        TypedQuery<Carrera> query = em.createQuery("SELECT c FROM Carrera c JOIN c.inscripciones i GROUP BY c ORDER BY COUNT(i.estudiante) DESC", Carrera.class);
        return query.getResultList();
    }

}
