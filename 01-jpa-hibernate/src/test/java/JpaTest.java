import com.sn.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    EntityManagerFactory factory;
    @Before
    public void before(){
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    @Test
    public void Test(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Customer customer = new Customer();
        customer.setCustName("dwt");
        em.persist(customer);
        tx.begin();
    }
}
