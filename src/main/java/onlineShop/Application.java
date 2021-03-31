package onlineShop;

import onlineShop.log.PaymentAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import onlineShop.model.Cart;
import onlineShop.model.CartItem;
import onlineShop.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        // Method 1. XML-based configuration: Use XML tag <bean><bean/> to define beans
        // Method 2. Annotation-based configuration: Use @Component and @Autowired annotations to define beans

//        ApplicationContext context = new ClassPathXmlApplicationContext("payment.xml");


        // Method 3. Use Java-based bean configuration

//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//
//        PaymentAction paymentAction = (PaymentAction) context.getBean(PaymentAction.class);
//        paymentAction.hello();

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        /* SessionFactory */
        // We initialize the sessionFactory once and then reuse it.
        // SessionFactory instance is used to get the Session objects for database operations.
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

        /* Session */
        // Session object is the interface between java application code and hibernate framework
        // and provides methods for CRUD operations.
        Session session = sessionFactory.openSession();

        /* Transaction */
        // Transaction is an object used by the application to specify atomic units of work.
        session.beginTransaction();

        /* Summary of SessionFactory & Session & Transaction */
        // 1. SessionFactory maintains Session.
        // 2. We request a Session from SessionFactory.
        // 3. Session performs transactions to operate data in DBMS.

        Customer customer = new Customer();
        customer.setFirstName("yichen");
        customer.setLastName("wang");

        CartItem cartItem1 = new CartItem();
        cartItem1.setQuantity(1);
        cartItem1.setPrice(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setQuantity(2);
        cartItem2.setPrice(2);

        Cart cart = new Cart();
        cart.setCartItem(new ArrayList<>());
        cart.getCartItem().add(cartItem1);
        cart.getCartItem().add(cartItem2);

        customer.setCart(cart);

        session.save(customer);

        session.getTransaction().commit();
        session.close();

    }
}
