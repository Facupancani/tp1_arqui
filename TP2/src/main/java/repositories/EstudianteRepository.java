package repositories;

import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante> {

    private EntityManager em;

    public EstudianteRepository(EntityManager em) {
        this.em = em;
    }

    // ej 2a)
    // dar de alta un estudiante
    @Override
    public void insert(Estudiante obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            if (!(obj instanceof Estudiante)) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
            transaction.commit();
        } catch (PersistenceException e) {
            transaction.rollback();
            System.out.println("Error al insertar Estudiante: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Estudiante obj) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Estudiante findById(int id) {
        return em.find(Estudiante.class, id);
    }

    // ej 2c)
    // recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple
    public List<Estudiante> findAll() {
        String query = "SELECT e FROM Estudiante e ORDER BY e.apellido ASC";
        Query q = em.createQuery(query, Estudiante.class);
        return q.getResultList();
    }

    // ej 2d)
    // recuperar un estudiante, en base a su número de libreta universitaria.
    public Estudiante findByNroLibreta(int nroLibreta) {
        String query = "SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta";
        Query q = em.createQuery(query, Estudiante.class);
        q.setParameter("nroLibreta", nroLibreta);
        return (Estudiante) q.getSingleResult();
    }

    // ej 2e)
    // recuperar todos los estudiantes, en base a su género.
    public List<Estudiante> findByGenero(String genero){
        String query = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = em.createQuery(query, Estudiante.class);
        q.setParameter("genero", genero);
        return q.getResultList();
    }

    // ej 2g)
    // recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<Estudiante> findByCarreraCiudad(int idCarrera, String ciudad){
        String query = "SELECT e FROM Estudiante e JOIN Inscripcion i ON i.estudiante.nroDocumento = e.nroDocumento WHERE i.carrera.idCarrera = :idCarrera AND e.ciudadResidencia = :ciudadResidencia";
        Query q = em.createQuery(query, Estudiante.class);
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("ciudadResidencia", ciudad);
        return q.getResultList();
    }




}
