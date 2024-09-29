package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;

public class EstudianteRepository implements Repository<Estudiante> {
    @Override
    public void insert(EntityManager em, Estudiante obj) {

    }

    @Override
    public void update(EntityManager em, Estudiante obj) {

    }

    @Override
    public void delete(EntityManager em, int id) {

    }

    @Override
    public Estudiante findById(EntityManager em, int id) {
        return null;
    }
}
