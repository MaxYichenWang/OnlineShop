package onlineShop.log;

public class ServerLogger implements Logger {
    @Override
    public void log(String info) {
        System.out.println("Server log = " + info);
    }
}
