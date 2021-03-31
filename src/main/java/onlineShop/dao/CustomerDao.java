package onlineShop.dao;

import onlineShop.model.Authorities;
import onlineShop.model.Customer;
import onlineShop.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmailId(customer.getUser().getEmailId());
        Session session = null;

        try {
            // create a session using session factory
            session = sessionFactory.openSession();

            // use transaction to make sure all operations fail or succeed togeher
            session.beginTransaction();
            session.save(customer);
            session.save(authorities);

            // commit all operations at once
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // if transaction fails, roll back to previous state
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                // manually close session after use, if create a session object
                session.close();
            }
        }
    }

    public Customer getCustomerByUserName(String userName) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            user = (User) criteria.add(Restrictions.eq("emailId", userName)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // no need to manually close session, because session is created it try (...)
        if (user != null) {
            return user.getCustomer();
        }
        return null;
    }
}
