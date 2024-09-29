package repositories;

import entities.Carrera;

import javax.persistence.EntityManager;

public class CarreraRepository implements Repository<Carrera> {

    @Override
    public void insert(EntityManager em, Carrera obj) {

    }

    @Override
    public void update(EntityManager em, Carrera obj) {

    }

    @Override
    public void delete(EntityManager em, int id) {

    }

    @Override
    public Carrera findById(EntityManager em, int id) {
        return null;
    }
}
