package springboot.app.servicio;

import java.util.List;

public interface Servicio<T>{
    public List<T> findAll() throws Exception;
    public T findById(int id) throws Exception;
    public T save(T obj) throws Exception;
    public boolean delete(int id) throws Exception;
    public T update(int id, T obj) throws Exception;
}
