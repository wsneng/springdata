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
        // factory = Persistence.createEntityManagerFactory("openJpa");
    }

    @Test
    public void TestC(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("zrjm");

        em.persist(customer);

        tx.commit();
    }
    // 延迟查询
    @Test
    public void TestR_lazy(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.getReference(Customer.class, 1L);
        System.out.println("----------------");
        System.out.println(customer);

        tx.commit();
    }

    @Test
    public void TestR(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find(Customer.class, 1L);
        System.out.println("----------------");
        System.out.println(customer);

        tx.commit();
    }
    // 修改
    @Test
    public void TestU(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustId(7L);
        customer.setCustName("wc");

        /**
         * 如果指定了主键：
         *      更新：1.先查询      看是否存在
         *      如果有存在 看是否有变化 有变化更新  如果没变化就不更新
         *      不存在则插入
         * 如果没有指定主键：
         *      插入
         */
        em.merge(customer);
        tx.commit();
    }
    @Test
    public void TestU_jpql(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // String jpql = "UPDATE Customer set  custName = ?1 where custId = 3";
        // em.createQuery(jpql).setParameter(1,"ddl")
        //         .executeUpdate();
        String jpql = "UPDATE Customer set  custName=:name where custId=:id";
        em.createQuery(jpql).setParameter("name","wsn").setParameter("id",3L)
                .executeUpdate();
        tx.commit();
    }
    @Test
    public void TestU_SQL(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        String sql = "UPDATE cst_customer set  cust_name =:cname where cust_id =:id";
        em.createNativeQuery(sql)
                .setParameter("cname","poi")
                .setParameter("id",3L)
                .executeUpdate();
        tx.commit();
    }
    @Test
    public void TestU_Del(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        /**
         * java.lang.IllegalArgumentException: Removing a detached instance com.sn.pojo.Customer#3
         * 不能删除游离状态的对象  必须查询出来再删除
         */
        // Customer customer = new Customer();
        // customer.setCustId(3L);
        // em.remove(customer);

        Customer customer = em.find(Customer.class, 7L);
        em.remove(customer);
        tx.commit();
    }
    @Test
    public void TestStatus(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();   //  临时状态（瞬时状态）  新创建出来的
        customer.setCustId(3L);        //游离状态  与entityManager没有关系
        em.find(Customer.class,5L);           // 持久化状态
        em.remove(customer);          // 删除状态（销毁状态）
        tx.commit();
    }
    @Test
    public void TestStatus02(){
        EntityManager  em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find(Customer.class, 5L);// 持久化状态  （持久化状态进行修改会同步数据库）
        //把持久化状态当做实实在在的数据库记录  会修改数据库
        System.out.println("11"+ customer);
        customer.setCustName("we");
        System.out.println(customer);
        Customer customer1 = em.find(Customer.class, 5L);
        System.out.println(customer1);
        tx.commit();
    }


}
