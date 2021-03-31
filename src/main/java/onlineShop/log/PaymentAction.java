package onlineShop.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class PaymentAction {

//    @Autowired
    private Logger logger;

    public PaymentAction(Logger logger) {
        System.out.println("call PaymentAction()");
        this.logger = logger;
    }

//    @Autowired
//    public void setLogger(Logger logger) {
//        this.logger = logger;
//    }

    public void hello() {
        logger.log("Hello World");
    }
}
