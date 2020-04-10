package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static EntityManagerFactory factoryUtil;

    private EntityManagerFactoryUtil(){}

    public static EntityManager getEntityManager(){
        if (factoryUtil == null){
            factoryUtil = Persistence.createEntityManagerFactory("persons");
        }
        return factoryUtil.createEntityManager();
    }
}
