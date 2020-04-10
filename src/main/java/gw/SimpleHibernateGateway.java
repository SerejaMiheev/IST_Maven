package gw;

import exceptions.EntityNotFound;
import utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class SimpleHibernateGateway<T> implements Gateway<T> {
    private EntityManager em = EntityManagerFactoryUtil.getEntityManager();
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public SimpleHibernateGateway(){
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> all(){
        return em.createQuery("select t from " + persistentClass.getSimpleName() + " t").getResultList();
    }

    @Override
    public T find(Long id) throws EntityNotFound {
        T person = em.find(persistentClass, id);
        if (person == null){
            throw new EntityNotFound(String.format("Entity with id=%d not found", id ));
        }
        return person;
    }

    @Override
    public void insert(T object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    @Override
    public void update(T object) {
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T object){
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
    }
}
