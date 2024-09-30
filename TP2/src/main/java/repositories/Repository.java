package repositories;

import javax.persistence.EntityManager;

public interface Repository <T> {

    void insert(T obj);
    void update(T obj);
    void delete(int id);
    T findById(int id);

}
