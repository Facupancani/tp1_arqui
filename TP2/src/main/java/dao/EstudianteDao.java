package dao;

import entities.Estudiante;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstudianteDao {

    private EntityManager em;

    public EstudianteDao(EntityManager em) {
        this.em = em;
    }

    // ej 2a)
    public void insert(Estudiante estudiante) {
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
    }

    public List<Estudiante> findAll(){
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
        return query.getResultList();
    }

    //ej 2c)
    // orderBy = campo
    // dir = ASC / DESC
    public List<Estudiante> findAll(String orderBy, String dir){
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ORDER BY "+orderBy+" "+dir, Estudiante.class);
        return query.getResultList();
    }

    // ej 2d)
    public Estudiante buscarPorLibreta(int nroLibreta) {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta", Estudiante.class);
        query.setParameter("nroLibreta", nroLibreta);
        return query.getSingleResult();
    }

    // ej 2e)
    public List<Estudiante> listarEstudiantesPorGenero(String genero) {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class);
        query.setParameter("genero", genero);
        return query.getResultList();
    }

}
