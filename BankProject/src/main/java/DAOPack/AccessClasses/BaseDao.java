package DAOPack.AccessClasses;


import utils.ConnectionsUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseDao<T> {

    public void add(T t) {
        EntityManager manager = ConnectionsUtils.factory.createEntityManager();
        manager.getTransaction().begin();
        try{
            manager.persist(t);
            manager.getTransaction().commit();
        } catch (Exception ex) {
            manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public Object find(Class<?> cls, Serializable id) {
        EntityManager manager = ConnectionsUtils.factory.createEntityManager();
        manager.getTransaction().begin();
        try{
            return manager.find(cls,id);

        } catch (Exception ex) {
            if ((manager.getTransaction().isActive()))
                manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }

    public void delete(Class<?> cls, Serializable id) {
        EntityManager manager = ConnectionsUtils.factory.createEntityManager();
        manager.getTransaction().begin();
        try{
            final Object object = manager.getReference(cls, id);
            manager.remove(cls.cast(object));
            manager.getTransaction().commit();

        } catch (Exception ex) {
            if ((manager.getTransaction().isActive()))
                manager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
