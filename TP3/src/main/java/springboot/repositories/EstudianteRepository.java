package springboot.repositories;

import springboot.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    List<Estudiante> findAllWithOrder();

    @Query("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta")
    Estudiante findByNroLibreta(@Param("nroLibreta") int nroLibreta);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
    List<Estudiante> findByGenero(@Param("genero") String genero);

    @Query("SELECT e FROM Estudiante e JOIN e.inscripciones i WHERE i.carrera.idCarrera = :carreraId AND e.ciudadResidencia = :ciudad")
    List<Estudiante> findByCarreraCiudad(int carreraId, String ciudad);

    @Query("SELECT e FROM Estudiante e WHERE e.nroDocumento = :nroDocumento")
    Estudiante findByNroDocumento(@Param("nroDocumento") int nroDocumento);


    /*
    private EntityManager em;

    public EstudianteRepository(EntityManager em) {
        this.em = em;
    }

    // ej 2a)
    // dar de alta un estudiante
    @Override
    public Estudiante insert(Estudiante obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try{
            em.persist(obj);
            transaction.commit();
        }catch (PersistenceException e){
            transaction.rollback();
            throw e;
        }

        return obj;

    }

    @Override
    public Estudiante update(int id, Estudiante obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
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
        try{
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta", Estudiante.class)
                    .setParameter("nroLibreta", nroLibreta)
                    .getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Estudiante findByNroDocumento(int nroDocumento) throws PersistenceException {
        try{
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.nroDocumento = :nroDocumento", Estudiante.class)
            .setParameter("nroDocumento", nroDocumento)
            .getSingleResult();
        }catch (PersistenceException e) {
            System.out.println(e.getMessage());
            throw e;
        }
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
    public List<Estudiante> findByCarreraCiudad(Carrera carrera, String ciudad) {
        String query = "SELECT e FROM Estudiante e " +
                "JOIN e.inscripciones i " +
                "WHERE i.carrera = :carrera AND e.ciudadResidencia = :ciudad";

        Query q = em.createQuery(query);
        q.setParameter("carrera", carrera);
        q.setParameter("ciudad", ciudad);

        return q.getResultList();
    }*/

}
