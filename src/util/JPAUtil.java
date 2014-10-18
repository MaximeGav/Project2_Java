package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MAXIME
 */
public class JPAUtil {

    private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("Persistence");

    public static EntityManagerFactory getEntityManagerFactory() {
        return em;
    }

    private JPAUtil() {
    }
}