package onlineShop.dao;

import onlineShop.model.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Cart getCartById(int cartId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cart.class, cartId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
