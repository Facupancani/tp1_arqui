package repositories;

import entities.Inscripcion;

import javax.persistence.EntityManager;

public class InscripcionRepository implements Repository<Inscripcion> {

    private EntityManager em;

    public InscripcionRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Inscripcion obj) {
        em.persist(obj);
    }

    @Override
    public void update(Inscripcion obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Inscripcion findById(int id) {
        return em.find(Inscripcion.class, id);
    }
}
