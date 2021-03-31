package onlineShop.log;

import org.springframework.stereotype.Component;

//@Component
public class FileLogger implements Logger{

    @Override
    public void log(String info) {
        System.out.println("File Log = " + info);
    }
}
