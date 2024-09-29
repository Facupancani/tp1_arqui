package dao;

import entities.Estudiante;
import entities.Inscripcion;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class InscripcionDao {

    private EntityManager em;

    public InscripcionDao(EntityManager em) {
        this.em = em;
    }

    // ej 2b)
    public void insert(Inscripcion inscripcion) {
        em.getTransaction().begin();
        em.persist(inscripcion);
        em.getTransaction().commit();
    }

    public List<Inscripcion> findAll(){
        TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
        return query.getResultList();
    }

    // ej 2g)
    public List<Estudiante> listarEstudiantesPorCarreraYCiudad(int carreraId, String ciudad) {
        TypedQuery<Estudiante> query = em.createQuery(
                "SELECT e FROM Inscripcion i JOIN i.estudiante e WHERE i.carrera.id = :carreraId AND e.ciudadResidencia = :ciudad", Estudiante.class);
        query.setParameter("carreraId", carreraId);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }

}
