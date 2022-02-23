import com.sn.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HibernateTest {
    // Session工厂  Session：数据库回话， 代码持久化操作数据库的一个桥梁

    private SessionFactory sf;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();

        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testC() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = new Customer();
            customer.setCustName("wsn");
            session.save(customer);
            tx.commit();
        } finally {
            session.close();
        }

    }

    @Test
    public void testR() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = session.find(Customer.class, 1L);
            System.out.println("=====================");
            System.out.println(customer);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testR_load() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = session.load(Customer.class, 1L);
            System.out.println("======懒加载===============");
            System.out.println(customer);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testU() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = new Customer();
            // customer.setCustId(1L);
            customer.setCustName("wsn");
            session.saveOrUpdate(customer);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testD() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Customer customer = new Customer();
            customer.setCustId(2L);

            session.remove(customer);
            tx.commit();
        } finally {
            session.close();
        }
    }

    @Test
    public void testR_HQL() {
        // session进行持久化操作
        Session session = sf.openSession();
        try {
            Transaction tx = session.beginTransaction();
            String hql = " FROM Customer WHERE custName like :custName";
            List<Customer> resultList = session.createQuery(hql, Customer.class)
                    .setParameter("custName", "w%")
                    .getResultList();
            System.out.println(resultList);
            tx.commit();
        } finally {
            session.close();
        }
    }

}
