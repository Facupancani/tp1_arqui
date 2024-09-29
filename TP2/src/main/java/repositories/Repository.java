package repositories;

import javax.persistence.EntityManager;

public interface Repository <T> {

    void insert(EntityManager em, T obj);
    void update(EntityManager em, T obj);
    void delete(EntityManager em, int id);
    T findById(EntityManager em, int id);

}
