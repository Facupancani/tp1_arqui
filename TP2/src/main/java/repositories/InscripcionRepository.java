package repositories;

import entities.Inscripcion;

import javax.persistence.EntityManager;

public class InscripcionRepository implements Repository<Inscripcion> {
    @Override
    public void insert(EntityManager em, Inscripcion obj) {

    }

    @Override
    public void update(EntityManager em, Inscripcion obj) {

    }

    @Override
    public void delete(EntityManager em, int id) {

    }

    @Override
    public Inscripcion findById(EntityManager em, int id) {
        return null;
    }
}
