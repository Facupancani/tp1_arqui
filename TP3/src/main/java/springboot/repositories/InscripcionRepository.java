package springboot.repositories;

import springboot.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    /*
    private EntityManager em;

    public InscripcionRepository(EntityManager em){
        this.em = em;
    }

    // ej 2b)
    // matricular un estudiante en una carrera
    @Override
    public Inscripcion insert(Inscripcion obj) {
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
    public Inscripcion update(int id, Inscripcion obj) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public List<Inscripcion> findAll() {
        String query = "SELECT e FROM Inscripcion e";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Inscripcion findById(int id) {
        return em.find(Inscripcion.class, id);
    }
    */

}
