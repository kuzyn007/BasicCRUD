package pl.seweryn.dao;
 
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import pl.seweryn.model.Book;
 
@RequestScoped
public class BookDaoImpl implements BookDao {
 
    @PersistenceUnit(name = "myPersistenceUnit")
    private EntityManagerFactory emFactory;
 
    @Override
    public void save(Book book) {
        EntityManager entityManager = emFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(book);
        tx.commit();
        entityManager.close();
    }
 
    @Override
    public Book get(Long id) {
        EntityManager entityManager = emFactory.createEntityManager();
        Book book = entityManager.find(Book.class, id);
        entityManager.close();
        return book;
    }
    
    @Override
    public void update(Book book) {
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(book);
        tx.commit();
        em.close();
    }
    
    @Override
    public void remove(Long bookId) {
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Book objToRemove = em.find(Book.class, bookId);
        tx.begin();
        em.remove(objToRemove);
        tx.commit();
        em.close();
        System.out.println("Usuniêto obiekt o id: " + objToRemove.getId());
    }
}